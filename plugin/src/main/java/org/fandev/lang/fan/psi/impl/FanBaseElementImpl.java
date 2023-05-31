package org.fandev.lang.fan.psi.impl;

import javax.annotation.Nonnull;

import consulo.language.ast.ASTNode;
import consulo.language.impl.psi.stub.StubBasedPsiElementBase;
import consulo.language.psi.stub.IStubElementType;
import org.fandev.index.FanIndex;
import org.fandev.lang.fan.FanLanguage;
import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import consulo.language.Language;
import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.StubElement;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 3:45:31 PM
 */
public class FanBaseElementImpl<T extends StubElement> extends StubBasedPsiElementBase<T> implements FanElement
{
	public FanBaseElementImpl(final T t, @Nonnull final IStubElementType iStubElementType)
	{
		super(t, iStubElementType);
	}

	public FanBaseElementImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	@Nonnull
	public Language getLanguage()
	{
		return FanLanguage.INSTANCE;
	}

	@Override
	public PsiElement getParent()
	{
		return getParentByStub();
	}

	protected FanTypeDefinition getFanObjType()
	{
		return getFanTypeByName("Obj");
	}

	protected FanTypeDefinition getVoidType()
	{
		return getFanTypeByName("Void");
	}

	protected FanTypeDefinition getFanTypeByName(final String name)
	{
		final FanIndex index = getProject().getComponent(FanIndex.class);
		final FanFile objFile = index.getFanFileByTypeName(name);

		if(objFile != null)
		{
			final FanTypeDefinition[] typeDefinitions = objFile.getTypeDefinitions();
			if(typeDefinitions != null && typeDefinitions.length == 1)
			{
				return typeDefinitions[0];
			}
		}
		return null;
	}
}
