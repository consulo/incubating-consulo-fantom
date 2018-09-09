package org.fandev.lang.fan.psi.impl.types;

import java.util.ArrayList;
import java.util.List;

import org.fandev.index.FanIndex;
import org.fandev.lang.fan.FanFileType;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.FanResolveResult;
import org.fandev.lang.fan.psi.api.modifiers.FanModifier;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.lang.fan.psi.impl.FanReferenceElementImpl;
import org.fandev.lang.fan.psi.impl.FanResolveResultImpl;
import org.fandev.utils.PsiUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.roots.ContentIterator;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import consulo.psi.PsiPackage;

/**
 * @author Dror Bereznitsky
 * @date Feb 19, 2009 11:32:13 PM
 */
public class FanCodeReferenceElementImpl extends FanReferenceElementImpl implements FanCodeReferenceElement
{
	private static final FanResolver RESOLVER = new FanResolver();

	public FanCodeReferenceElementImpl(final StubElement stubElement, @NotNull final IStubElementType iStubElementType)
	{
		super(stubElement, iStubElementType);
	}

	public FanCodeReferenceElementImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public String toString()
	{
		return "Reference element";
	}

	@Override
	public PsiElement getQualifier()
	{
		return this;
	}

	@Override
	public PsiElement resolve()
	{
		final ResolveResult[] results = ResolveCache.getInstance(getProject()).resolveWithCaching(this, RESOLVER, false, false);
		return results.length == 1 ? results[0].getElement() : null;
	}

	@NotNull
	@Override
	public String getCanonicalText()
	{
		final PsiElement resolved = resolve();
		if(resolved instanceof FanTypeDefinition)
		{
			return ((FanTypeDefinition) resolved).getQualifiedName();
		}
		if(resolved instanceof PsiPackage)
		{
			return ((PsiPackage) resolved).getQualifiedName();
		}
		return null;
	}

	@Override
	public boolean isReferenceTo(final PsiElement psiElement)
	{
		return getManager().areElementsEquivalent(psiElement, resolve());
	}

	@NotNull
	@Override
	public Object[] getVariants()
	{
		final FanCodeReferenceElement qualifier = (FanCodeReferenceElement) getQualifier();
		if(qualifier != null)
		{
			final PsiElement resolve = qualifier.resolve();
			if(resolve instanceof FanTypeDefinition)
			{
				final FanTypeDefinition clazz = (FanTypeDefinition) resolve;
				final List<PsiElement> result = new ArrayList<PsiElement>();

				for(final FanField field : clazz.getFanFields())
				{
					if(field.hasModifierProperty(FanModifier.STATIC))
					{
						result.add(field);
					}
				}

				for(final FanMethod method : clazz.getFanMethods())
				{
					if(method.hasModifierProperty(FanModifier.STATIC))
					{
						result.add(method);
					}
				}

				return result.toArray(new PsiElement[0]);
			}
		}
		return new Object[]{};
	}

	@Override
	public boolean isSoft()
	{
		return false;
	}

	@Override
	@NotNull
	public ResolveResult[] multiResolve(final boolean incompleteCode)
	{
		return ResolveCache.getInstance(getProject()).resolveWithCaching(this, RESOLVER, false, incompleteCode);
	}

	private static class FanResolver implements ResolveCache.PolyVariantResolver<FanCodeReferenceElementImpl>
	{

		@NotNull
		@Override
		public ResolveResult[] resolve(final FanCodeReferenceElementImpl fanCodeReferenceElement, final boolean incompleteCode)
		{
			if(fanCodeReferenceElement.getReferenceName() == null)
			{
				return FanResolveResult.EMPTY_ARRAY;
			}

			final FanResolveResult[] results = _resolve(fanCodeReferenceElement, fanCodeReferenceElement.getManager());

			return results;
		}

		//TODO handle other possbile reference types: Enum, Mixin
		private FanResolveResult[] _resolve(final FanCodeReferenceElementImpl ref, final PsiManager manager)
		{
			final String refName = ref.getReferenceName();
			final FanCodeReferenceElement qualifier = (FanCodeReferenceElement) ref.getQualifier();
			if(qualifier != null)
			{
				final List<FanResolveResult> results = new ArrayList<FanResolveResult>();
				ProjectRootManager.getInstance(manager.getProject()).getFileIndex().iterateContent(new ContentIterator()
				{
					@Override
					public boolean processFile(final VirtualFile virtualFile)
					{
						if(FanFileType.INSTANCE == virtualFile.getFileType())
						{
							final FanFile psiFile = (FanFile) manager.findFile(virtualFile);
							final FanTypeDefinition[] classes = psiFile.getTypeDefinitions();
							for(final FanTypeDefinition aClass : classes)
							{
								if(refName.equals(aClass.getName()))
								{
									final boolean isAccessible = PsiUtil.isAccessible(ref, aClass);
									results.add(new FanResolveResultImpl(aClass, isAccessible));
								}
							}
						}
						return true;
					}
				});

				if(results.size() > 0)
				{
					return results.toArray(new FanResolveResult[0]);
				}

				final FanIndex fanIndex = (FanIndex) manager.getProject().getComponent(FanIndex.class);
				final PsiFile typeFile = fanIndex.getFanFileByTypeName(refName);
				if(typeFile != null)
				{
					final FanFile psiFile = (FanFile) typeFile;
					final FanTypeDefinition[] classes = psiFile.getTypeDefinitions();
					for(final FanTypeDefinition aClass : classes)
					{
						try
						{
							if(refName.equals(aClass.getName()))
							{
								final boolean isAccessible = PsiUtil.isAccessible(ref, aClass);
								results.add(new FanResolveResultImpl(aClass, isAccessible));
							}
						}
						catch(Exception e)
						{
							continue;
						}
					}
				}

				return results.toArray(new FanResolveResult[0]);
			}
			return FanResolveResult.EMPTY_ARRAY;
		}
	}
}