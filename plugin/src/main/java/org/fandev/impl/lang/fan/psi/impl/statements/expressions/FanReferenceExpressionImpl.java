package org.fandev.impl.lang.fan.psi.impl.statements.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import consulo.language.psi.ResolveResult;
import consulo.language.psi.stub.StubIndex;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.language.util.IncorrectOperationException;
import org.fandev.index.FanIndex;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.FanType;
import org.fandev.impl.lang.fan.psi.api.FanResolveResult;
import org.fandev.impl.lang.fan.psi.api.modifiers.FanModifier;
import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.statements.expressions.FanClosureExpression;
import org.fandev.lang.fan.psi.api.statements.expressions.FanExpression;
import org.fandev.impl.lang.fan.psi.api.statements.expressions.FanIndexExpression;
import org.fandev.impl.lang.fan.psi.api.statements.expressions.FanReferenceExpression;
import org.fandev.impl.lang.fan.psi.api.statements.expressions.FanSuperReferenceExpression;
import org.fandev.impl.lang.fan.psi.api.statements.expressions.FanThisReferenceExpression;
import org.fandev.impl.lang.fan.psi.api.statements.expressions.FanTypeReferenceExpression;
import org.fandev.impl.lang.fan.psi.api.statements.expressions.PodReferenceExpression;
import org.fandev.lang.fan.psi.api.statements.params.FanFormal;
import org.fandev.lang.fan.psi.api.statements.params.FanFormals;
import org.fandev.lang.fan.psi.api.statements.params.FanParameter;
import org.fandev.lang.fan.psi.api.statements.params.FanParameterList;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.impl.lang.fan.psi.api.statements.typeDefs.members.FanConstructor;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMember;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.impl.lang.fan.psi.impl.FanClassReferenceType;
import org.fandev.impl.lang.fan.psi.impl.FanFuncType;
import org.fandev.lang.fan.psi.impl.FanListReferenceType;
import org.fandev.lang.fan.psi.impl.FanMapType;
import org.fandev.impl.lang.fan.psi.impl.FanReferenceElementImpl;
import org.fandev.impl.lang.fan.psi.impl.FanResolveResultImpl;
import org.fandev.lang.fan.psi.stubs.index.FanShortClassNameIndex;
import org.fandev.utils.FanUtil;
import org.fandev.impl.utils.PsiUtil;
import org.jetbrains.annotations.NonNls;
import consulo.language.ast.ASTNode;
import consulo.logging.Logger;
import consulo.language.psi.PsiElement;
import consulo.language.psi.resolve.ResolveCache;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.StubElement;

/**
 * Date: Jun 24, 2009
 * Time: 11:48:37 PM
 *
 * @author Dror Bereznitsky
 */
public class FanReferenceExpressionImpl extends FanReferenceElementImpl implements FanReferenceExpression
{
	private static final OurResolver RESOLVER = new OurResolver();

	private static final Logger logger = Logger.getInstance(FanReferenceExpressionImpl.class.getName());

	public FanReferenceExpressionImpl(final StubElement stubElement, @Nonnull final IStubElementType iStubElementType)
	{
		super(stubElement, iStubElementType);
	}

	public FanReferenceExpressionImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public PsiElement getQualifier()
	{
		return this;
	}

	@Override
	public boolean isReferenceTo(final PsiElement psiElement)
	{
		for(final Object obj : getVariants())
		{
			if(psiElement.equals(obj))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public PsiElement setName(@Nonnull @NonNls final String s) throws IncorrectOperationException
	{
		//TODO [Dror] implement
		return null;
	}

	@Nonnull
	@Override
	public Object[] getVariants()
	{
		final List<PsiElement> result = new ArrayList<PsiElement>();
		final FanReferenceExpression qualifier = (FanReferenceExpression) getQualifier();
		if(qualifier != null)
		{
			final ResolveResult[] results = qualifier.multiResolve(true);
			if(results.length > 0)
			{
				for(final ResolveResult tmp : results)
				{
					result.add(tmp.getElement());
				}
				return result.toArray(new PsiElement[0]);
			}
		}
		return new Object[0];
	}

	@Override
	public boolean isSoft()
	{
		return false;
	}

	@Nonnull
	@Override
	public String getCanonicalText()
	{
		return null;
	}

	@Override
	@Nonnull
	//incomplete means we do not take arguments into consideration
	public ResolveResult[] multiResolve(final boolean incompleteCode)
	{
		return ResolveCache.getInstance(getProject()).resolveWithCaching(this, RESOLVER, false, incompleteCode);
	}

	@Override
	public PsiElement resolve()
	{
		final ResolveResult[] results = ResolveCache.getInstance(getProject()).resolveWithCaching(this, RESOLVER, false, false);
		return results.length == 1 ? results[0].getElement() : null;
	}

	@Override
	public FanExpression getQualifierExpression()
	{
		return findChildByClass(FanExpression.class);
	}

	@Override
	@Nonnull
	public FanResolveResult[] getSameNameVariants()
	{
		return RESOLVER.resolve(this, true);
	}

	private static class OurResolver implements ResolveCache.PolyVariantResolver<FanReferenceExpressionImpl>
	{
		//TODO [Dror] refactor this complex, ugly, HUGE method !!!
		@Nonnull
		@Override
		public FanResolveResult[] resolve(@Nonnull final FanReferenceExpressionImpl refExpr, final boolean incompleteCode)
		{
			final List<FanResolveResult> results = new ArrayList<FanResolveResult>();

			final ResolveHint hint = new ResolveHint(refExpr);

			FanTypeDefinition typeDefinition = null;
			FanClassReferenceType referenceType = null;

			if(hint.isSuperRefrenceExpression() || hint.isThisRefrenceExpression())
			{
				// "super" or "this" reference
				if(hint.gotoNonFieldOrMethodDecleration())
				{
					return FanResolveResult.EMPTY_ARRAY;
				}

				typeDefinition = ((FanTypeReferenceExpression) hint.idElement).getReferencedType();
			}
			else if(hint.someTypeFieldOrMethodRef())
			{
				typeDefinition = resolveTypeDefinition(hint, typeDefinition);
			}
			else
			{
				// methods and fields
				if(hint.containingTypeFieldOrMethodRef())
				{
					final PsiElement maybeClazz = PsiTreeUtil.getParentOfType(refExpr, FanTypeDefinition.class, FanFile.class);
					if(FanUtil.isFanType(maybeClazz))
					{
						resolveByType(results, hint, (FanTypeDefinition) maybeClazz);
					}
				}
				// Find other options in the containing block
				PsiElement containingBlock = refExpr.getParent();
				while(!(FanUtil.isFanFile(containingBlock)))
				{
					if(FanUtil.isFanClosure(containingBlock))
					{
						final FanFormals fanFormals = ((FanClosureExpression) containingBlock).getFunction().getFormals();
						final String toMatch = stripRefElement(hint.idElement);
						for(final FanFormal formal : fanFormals.getParameters())
						{
							if(toMatch.equals(formal.getName()))
							{
								if(hint.gotoNonFieldOrMethodDecleration())
								{
									// Goto formal decleration
									return new FanResolveResult[]{new FanResolveResultImpl(formal, true)};
								}
								else
								{
									referenceType = (FanClassReferenceType) formal.getType();
									break;
								}
							}
							else if(hint.completeIdentifier() && formal.getName().startsWith(toMatch))
							{
								results.add(new FanResolveResultImpl(formal, true));
							}
						}
					}
					else if(FanUtil.isFanMethod(containingBlock))
					{
						final FanParameterList params = ((FanMethod) containingBlock).getParameterList();
						for(final FanParameter param : params.getParameters())
						{
							String elementText = hint.idElement.getText();
							if(elementText.indexOf("(") != -1)
							{
								elementText = elementText.substring(0, elementText.indexOf("("));
							}
							if(param.getName().equals(elementText))
							{
								if(hint.gotoNonFieldOrMethodDecleration())
								{
									// Goto parameter decleration
									return new FanResolveResult[]{new FanResolveResultImpl(param, true)};
								}
								else
								{
									referenceType = (FanClassReferenceType) param.getType();
									break;
								}
							}
						}
					}
					else if(FanUtil.isPsiCodeBlock(containingBlock))
					{
						// Look for a local variable inside code block
						List<FanVariable> variables;
						if(hint.gotoDecleration && !hint.fieldOrMethodeRef && !hint.isMethodRef)
						{
							variables = findVariablesByName(hint.idElement, containingBlock, true);
							// Goto variable decleration
							if(variables.size() > 0)
							{
								return new FanResolveResult[]{new FanResolveResultImpl(variables.get(0), true)};
							}
						}
						else if(hint.gotoDecleration && !hint.fieldOrMethodeRef && hint.isMethodRef)
						{
							// Function call return type
							variables = findVariablesByName(hint.idElement, containingBlock, true);
							if(variables.size() > 0)
							{
								final FanType type = variables.get(0).getType();
								if(type instanceof FanFuncType)
								{
									final FanType returnType = ((FanFuncType) type).getReturnType();
									return new FanResolveResult[]{new FanResolveResultImpl(((FanClassReferenceType) returnType).resolveFanType(), true)};
								}
								//break;
							}
						}
						else if(!hint.gotoDecleration && !hint.fieldOrMethodeRef)
						{
							variables = findVariablesByName(hint.idElement, containingBlock, false);
							if(variables.size() > 0)
							{
								results.addAll(Arrays.asList(createResults(refExpr, variables.toArray(new FanVariable[0]))));
								//break;
							}
						}
						else
						{
							// Variable type
							variables = findVariablesByName(hint.idElement, containingBlock, true);
							if(variables.size() > 0)
							{
								final FanType type = variables.get(0).getType();
								if(type instanceof FanClassReferenceType)
								{
									referenceType = (FanClassReferenceType) type;
								}
								break;
							}
						}
					}
					else if(FanUtil.isFanType(containingBlock))
					{
						// Look for a field inside the Fan type hierarchy
						FanTypeDefinition myClass = (FanTypeDefinition) containingBlock;
						while(myClass instanceof FanTypeDefinition)
						{
							if(hint.isMethodRef && !hint.fieldOrMethodeRef)
							{
								final FanMethod method = myClass.getMethodByName(hint.toMatch);
								if(method != null && hint.gotoDecleration)
								{
									return createResults(refExpr, method);
								}
							}
							else
							{
								final FanField field = myClass.getFieldByName(hint.idElement.getText());
								if(field != null)
								{
									// If we need to goto the reference
									if(hint.gotoNonFieldOrMethodDecleration())
									{
										return createResults(refExpr, field);
									}
									// Else we are looking for this field type
									referenceType = (FanClassReferenceType) field.getType();
									break;
								}
								else
								{
									final FanMethod method = myClass.getMethodByName(hint.toMatch);
									if(method != null && hint.gotoDecleration)
									{
										return createResults(refExpr, method);
									}
								}
							}
							myClass = myClass.getSuperType(); // super class of Obj will is null
						}
					}
					containingBlock = containingBlock.getParent();
				}

				if(referenceType != null)
				{
					typeDefinition = referenceType.resolveFanType();
				}
			}

			if(typeDefinition != null)
			{
				resolveByType(results, hint, typeDefinition);
			}
			else if(referenceType != null)
			{
				logger.warn("Could not resolve type " + referenceType.getPresentableText());
			}
			else if(results.size() == 0 && !hint.fieldOrMethodeRef)
			{
				resolveTypes(results, hint);
			}

			return results.toArray(new FanResolveResult[0]);
		}

		private FanTypeDefinition resolveTypeDefinition(final ResolveHint hint, FanTypeDefinition typeDefinition)
		{
			if(!(hint.idElement instanceof FanReferenceExpression))
			{
				throw new IllegalArgumentException("hint idElement must be instance of FanReferenceExpression");
			}

			final PsiElement typeProvider = ((FanReferenceExpression) hint.idElement).resolve();
			FanType type = null;
			if(FanUtil.isFanMethod(typeProvider))
			{
				type = ((FanMethod) typeProvider).getReturnType();
			}
			else if(FanUtil.isFanField(typeProvider))
			{
				type = ((FanField) typeProvider).getType();
			}
			else if(FanUtil.isFanVariable(typeProvider))
			{
				type = ((FanVariable) typeProvider).getType();
			}
			else if(FanUtil.isFanTypeDefinition(typeProvider))
			{
				// If the provider is not the return value of a function call
				if(!hint.idElement.getText().contains("("))
				{
					hint.isStatic = true;
				}
				typeDefinition = (FanTypeDefinition) typeProvider;
			}

			if(type instanceof FanClassReferenceType)
			{
				typeDefinition = ((FanClassReferenceType) type).resolveFanType();
			}
			else if(type instanceof FanFuncType)
			{
				// if this is a function call than use the function return type
				if(hint.idElement.getText().contains("("))
				{
					final FanType returnType = ((FanFuncType) type).getReturnType();
					typeDefinition = ((FanClassReferenceType) returnType).resolveFanType();
				}
				else
				{
					// Treat is as a Fan Func type
					typeDefinition = ((FanFuncType) type).getFuncType();
				}
			}
			else if(FanUtil.isFanMapType(type))
			{
				typeDefinition = ((FanMapType) type).getMapType();
			}
			else if(FanUtil.isFanListType(type))
			{
				if(hint.isIndex)
				{
					typeDefinition = (FanTypeDefinition) ((FanListReferenceType) type).getTypeElement().getReferenceElement().resolve();
				}
				else
				{
					typeDefinition = ((FanListReferenceType) type).getListType();
				}
			}
			return typeDefinition;
		}

		private FanResolveResult[] createResults(final FanReferenceExpressionImpl refExpr, final PsiElement... element)
		{
			final List<FanResolveResult> results = new ArrayList<FanResolveResult>();
			for(final PsiElement elem : element)
			{
				boolean isAccessible = true;
				if(FanMember.class.isAssignableFrom(elem.getClass()))
				{
					isAccessible = PsiUtil.isAccessible(refExpr, (FanMember) elem);
				}
				results.add(new FanResolveResultImpl(elem, isAccessible));
			}
			return results.toArray(new FanResolveResult[0]);
		}

		/**
		 * Resolve slots in type 'typeDefinition' which match 'toMatch'
		 */
		private void resolveByType(final List<FanResolveResult> results, final ResolveHint hint, FanTypeDefinition typeDefinition)
		{
			final FanReferenceExpressionImpl context = hint.refExpr;

			boolean isSuperType = false;
			while(typeDefinition != null)
			{
				final FanMethod[] methods = hint.isStatic ? typeDefinition.getFanMethods(FanModifier.STATIC) : typeDefinition.getFanMethods();
				for(final FanMethod method : methods)
				{
					if((!hint.gotoDecleration && method.getName().startsWith(hint.toMatch)) || (hint.gotoDecleration && method.getName().equals(hint.toMatch)))
					{
						if(isSuperType && method instanceof FanConstructor)
						{
							continue;
						}

						final boolean isAccessible = PsiUtil.isAccessible(context, method);
						results.add(new FanResolveResultImpl(method, isAccessible));
					}
				}
				if(!hint.isMethodRef)
				{
					final FanField[] fields = hint.isStatic ? typeDefinition.getFanFields(FanModifier.STATIC) : typeDefinition.getFanFields();
					for(final FanField psiField : fields)
					{
						if((!hint.gotoDecleration && psiField.getName().startsWith(hint.toMatch)) || (hint.gotoDecleration && psiField.getName().equals(hint.toMatch)))
						{
							final boolean isAccessible = PsiUtil.isAccessible(context, psiField);
							results.add(new FanResolveResultImpl(psiField, isAccessible));
						}
					}
				}
				if(hint.isStatic && FanUtil.isFanEnumDefinition(typeDefinition))
				{
					final FanEnumValue[] enumValues = ((FanEnumDefinition) typeDefinition).getEnumValues();
					for(final FanEnumValue enumValue : enumValues)
					{
						if((!hint.gotoDecleration && enumValue.getName().startsWith(hint.toMatch)) || (hint.gotoDecleration && enumValue.getName().equals(hint
								.toMatch)))
						{
							final boolean isAccessible = PsiUtil.isAccessible(context, enumValue);
							results.add(new FanResolveResultImpl(enumValue, isAccessible));
						}
					}
				}
				typeDefinition = (FanTypeDefinition) typeDefinition.getSuperClass();
				isSuperType = true;
			}
		}

		private List<FanVariable> findVariablesByName(final PsiElement idElement, final PsiElement containingBlock, final boolean exectMatch)
		{
			final List<FanVariable> variables = new ArrayList<FanVariable>();
			final String toMatch = stripRefElement(idElement);
			for(final PsiElement psiElement : containingBlock.getChildren())
			{
				if(psiElement instanceof FanVariable)
				{
					final String name = ((FanVariable) psiElement).getName();
					if(name != null)
					{
						if(name.equals(toMatch))
						{
							if(variables.size() > 0)
							{
								variables.set(0, (FanVariable) psiElement);
							}
							else
							{
								variables.add((FanVariable) psiElement);
							}
						}
						else if(name.startsWith(toMatch) && !exectMatch)
						{
							variables.add((FanVariable) psiElement);
						}
					}
				}
			}
			return variables;
		}

		private String stripRefElement(final PsiElement idElement)
		{
			int idx = idElement.getText().indexOf("IntellijIdeaRulezzz");
			int idx2 = idElement.getText().indexOf("(");
			if(idx2 >= 0 && idx2 > idx)
			{
				idx = idx2;
			}

			final String toMatch = idx >= 0 ? idElement.getText().substring(0, idx) : idElement.getText();
			return toMatch;
		}

		private void resolvePodTypes(final FanReferenceExpressionImpl refExpr, final String podName, final String toMatch, boolean gotoDecleration,
				List<FanResolveResult> results)
		{
			final FanIndex index = refExpr.getProject().getComponent(FanIndex.class);

			final Set<FanTypeDefinition> typesStartingWith = index.getPodTypesStartingWith(podName, toMatch);
			for(final FanTypeDefinition def : typesStartingWith)
			{
				if(!gotoDecleration || (gotoDecleration && def.getName().equals(refExpr.getText())))
				{
					final boolean isAccessible = PsiUtil.isAccessible(refExpr, def);
					results.add(new FanResolveResultImpl(def, isAccessible));
				}
			}
		}

		private void resolveTypes(final List<FanResolveResult> results, final ResolveHint hint)
		{
			final FanIndex index = hint.refExpr.getProject().getComponent(FanIndex.class);

			// try to match pod names
			if(!hint.isFqn())
			{
				final Set<FanTypeDefinition> podDefinitions = index.getPodStartingWith(hint.toMatch);
				for(final FanTypeDefinition def : podDefinitions)
				{
					if(!hint.gotoDecleration || (hint.gotoDecleration && def.getName().equals(hint.refExpr.getText())))
					{
						results.add(new FanResolveResultImpl(def, true));
					}
				}
			}

			// Try to match type names
			final Collection<FanTypeDefinition> typesStartingWith = StubIndex.getInstance().get(FanShortClassNameIndex.KEY, hint.toMatch,
					hint.refExpr.getProject(), null);

			if(hint.isFqn())
			{
				final String podName = ((PodReferenceExpression) hint.idElement).getPodName();
				typesStartingWith.addAll(index.getPodTypesStartingWith(podName, hint.toMatch));
			}
			else
			{
				typesStartingWith.addAll(index.getAllTypesStartingWith(hint.toMatch));
			}

			for(final FanTypeDefinition def : typesStartingWith)
			{
				if(!hint.gotoDecleration || (hint.gotoDecleration && def.getName().equals(hint.refExpr.getText())))
				{
					final boolean isAccessible = PsiUtil.isAccessible(hint.refExpr, def);
					results.add(new FanResolveResultImpl(def, isAccessible));
				}
			}
		}
	}

	private static class ResolveHint
	{
		final boolean gotoDecleration;
		final boolean isMethodRef;
		final boolean fieldOrMethodeRef;
		final boolean isIndex;
		boolean isStatic;

		final String toMatch;
		final PsiElement idElement;
		final FanReferenceExpressionImpl refExpr;

		ResolveHint(final FanReferenceExpressionImpl refExpr)
		{
			final int idx = refExpr.getText().indexOf("IntellijIdeaRulezzz");
			// If we are not trying to complete but just return the PsiElement which is referenced (Go to declaration)
			gotoDecleration = idx == -1;
			String toMatch = idx >= 0 ? refExpr.getText().substring(0, idx) : refExpr.getText();
			isMethodRef = toMatch.contains("(");
			if(isMethodRef)
			{
				toMatch = toMatch.substring(0, toMatch.indexOf("("));
			}

			final PsiElement parent = refExpr.getParent();

			PsiElement isTheIdElem = refExpr;
			int index = -1;
			int idxExpIndex = -1;
			for(final PsiElement child : parent.getChildren())
			{
				index++;
				if(child == refExpr)
				{
					break;
				}
				if(FanUtil.isOfType(child, FanIndexExpression.class))
				{
					idxExpIndex = index;
					continue;
				}
				isTheIdElem = child;
			}
			idElement = isTheIdElem instanceof FanReferenceExpression ||
					isTheIdElem instanceof FanTypeReferenceExpression ||
					isTheIdElem instanceof PodReferenceExpression ? isTheIdElem : refExpr;
			fieldOrMethodeRef = !refExpr.equals(idElement) && !FanUtil.isOfType(idElement, PodReferenceExpression.class); // obj.fieldOrMethodeRef
			isIndex = index == idxExpIndex + 1;

			this.toMatch = toMatch;
			this.refExpr = refExpr;
			this.isStatic = false;
		}

		boolean isThisRefrenceExpression()
		{
			return idElement instanceof FanThisReferenceExpression;
		}

		boolean isSuperRefrenceExpression()
		{
			return idElement instanceof FanSuperReferenceExpression;
		}

		boolean gotoNonFieldOrMethodDecleration()
		{
			return gotoDecleration && !fieldOrMethodeRef;
		}

		boolean completeIdentifier()
		{
			return !gotoDecleration && !fieldOrMethodeRef;
		}

		// variable.fieldOrMethod
		boolean someTypeFieldOrMethodRef()
		{
			return idElement instanceof FanReferenceExpression && fieldOrMethodeRef;
		}

		boolean containingTypeFieldOrMethodRef()
		{
			return idElement instanceof FanReferenceExpression && !fieldOrMethodeRef;
		}

		boolean isFqn()
		{
			return FanUtil.isOfType(idElement, PodReferenceExpression.class);
		}
	}
}
