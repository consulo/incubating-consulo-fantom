package org.fandev.impl.lang.fan.psi.impl.statements.typedefs.members;

import javax.annotation.Nonnull;

import consulo.language.psi.stub.NamedStub;
import org.fandev.impl.icons.Icons;
import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.blocks.FanPsiCodeBlock;
import org.fandev.lang.fan.psi.api.statements.params.FanParameterList;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import consulo.language.ast.ASTNode;
import consulo.language.psi.stub.IStubElementType;
import consulo.ui.image.Image;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 6:59:12 PM
 */
public abstract class FanMethodBaseImpl<T extends NamedStub> extends FanSlotElementImpl<T> implements FanMethod
{
	public FanMethodBaseImpl(final T t, @Nonnull final IStubElementType iStubElementType)
	{
		super(t, iStubElementType);
	}

	public FanMethodBaseImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public Image getIconInner()
	{
		return Icons.METHOD;
	}

	@Override
	@Nonnull
	public FanType getReturnType()
	{
		final FanTypeElement typeElement = findChildByClass(FanTypeElement.class);
		if(typeElement != null)
		{
			return typeElement.getType();
		}
		return FanType.VOID;
	}

	@Nonnull
	@Override
	public FanParameterList getParameterList()
	{
		final FanParameterList parameterList = findChildByClass(FanParameterList.class);
		assert parameterList != null;
		return parameterList;
	}

	@Override
	public FanPsiCodeBlock getBody()
	{
		return findChildByClass(FanPsiCodeBlock.class);
	}

	@Override
	public void setBlock(FanPsiCodeBlock newBlock)
	{

	}

/*	public boolean isVarArgs()
	{
		return false;
	}

	@NotNull
	public MethodSignature getSignature(@NotNull final PsiSubstitutor substitutor)
	{
		return MethodSignatureBackedByPsiMethod.create(this, substitutor);
	}

	@NotNull
	public PsiMethod[] findSuperMethods()
	{
		final PsiClass containingClass = getContainingClass();
		if(containingClass == null)
		{
			return PsiMethod.EMPTY_ARRAY;
		}

		final Set<PsiMethod> methods = new HashSet<PsiMethod>();
		findSuperMethodRecursilvely(methods, containingClass, false, new HashSet<PsiClass>(), createMethodSignature(this),
				new HashSet<MethodSignature>());

		return methods.toArray(new PsiMethod[0]);
	}

	@NotNull
	public PsiMethod[] findSuperMethods(final boolean checkAccess)
	{
		final PsiClass containingClass = getContainingClass();

		final Set<PsiMethod> methods = new HashSet<PsiMethod>();
		findSuperMethodRecursilvely(methods, containingClass, false, new HashSet<PsiClass>(), createMethodSignature(this),
				new HashSet<MethodSignature>());

		return methods.toArray(new PsiMethod[0]);
	}

	@NotNull
	public PsiMethod[] findSuperMethods(final PsiClass parentClass)
	{
		final Set<PsiMethod> methods = new HashSet<PsiMethod>();
		findSuperMethodRecursilvely(methods, parentClass, false, new HashSet<PsiClass>(), createMethodSignature(this),
				new HashSet<MethodSignature>());
		return methods.toArray(new PsiMethod[0]);
	}

	@NotNull
	public List<MethodSignatureBackedByPsiMethod> findSuperMethodSignaturesIncludingStatic(final boolean checkAccess)
	{
		final PsiClass containingClass = getContainingClass();

		final Set<PsiMethod> methods = new HashSet<PsiMethod>();
		final MethodSignature signature = createMethodSignature(this);
		findSuperMethodRecursilvely(methods, containingClass, true, new HashSet<PsiClass>(), signature, new HashSet<MethodSignature>());

		final List<MethodSignatureBackedByPsiMethod> result = new ArrayList<MethodSignatureBackedByPsiMethod>();
		for(final PsiMethod method : methods)
		{
			result.add(method.getHierarchicalMethodSignature());
		}

		return result;
	}

	@NotNull
	public PsiMethod[] findDeepestSuperMethods()
	{
		return new PsiMethod[0];  //To change body of implemented methods use File | Settings | File Templates.
	}

	@NotNull
	public HierarchicalMethodSignature getHierarchicalMethodSignature()
	{
		return PsiSuperMethodImplUtil.getHierarchicalMethodSignature(this);
	}

	public void setBlock(final PsiCodeBlock newBlock)
	{
		final ASTNode newNode = newBlock.getNode().copyElement();
		final PsiCodeBlock oldBlock = getBody();
		if(oldBlock == null)
		{
			getNode().addChild(newNode);
			return;
		}
		getNode().replaceChild(oldBlock.getNode(), newNode);
	}

	public static MethodSignature createMethodSignature(final PsiMethod method)
	{
		final PsiParameter[] parameters = method.getParameterList().getParameters();
		final PsiType[] types = new PsiType[parameters.length];
		for(int i = 0; i < types.length; i++)
		{
			types[i] = parameters[i].getType();
		}
		return MethodSignatureUtil.createMethodSignature(method.getName(), types, PsiTypeParameter.EMPTY_ARRAY, PsiSubstitutor.EMPTY);
	}

	private void findSuperMethodRecursilvely(final Set<PsiMethod> methods, final PsiClass psiClass, final boolean allowStatic,
			final Set<PsiClass> visited, final MethodSignature signature, @NotNull final Set<MethodSignature> discoveredSupers)
	{
		if(psiClass == null)
		{
			return;
		}
		if(visited.contains(psiClass))
		{
			return;
		}
		visited.add(psiClass);
		final PsiClassType[] superClassTypes = psiClass.getSuperTypes();

		for(final PsiClassType superClassType : superClassTypes)
		{
			final PsiClass resolvedSuperClass = superClassType.resolve();

			if(resolvedSuperClass == null)
			{
				continue;
			}
			final PsiMethod[] superClassMethods = resolvedSuperClass.getMethods();
			final HashSet<MethodSignature> supers = new HashSet<MethodSignature>(3);

			for(final PsiMethod superClassMethod : superClassMethods)
			{
				final MethodSignature superMethodSignature = createMethodSignature(superClassMethod);

				if(PsiImplUtil.isExtendsSignature(superMethodSignature, signature))
				{
					if(allowStatic || !superClassMethod.getModifierList().hasExplicitModifier(PsiModifier.STATIC))
					{
						methods.add(superClassMethod);
						supers.add(superMethodSignature);
						discoveredSupers.add(superMethodSignature);
					}
				}
			}

			findSuperMethodRecursilvely(methods, resolvedSuperClass, allowStatic, visited, signature, discoveredSupers);
			discoveredSupers.removeAll(supers);
		}
	} */
}
