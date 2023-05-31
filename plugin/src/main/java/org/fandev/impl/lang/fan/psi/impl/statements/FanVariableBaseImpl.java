package org.fandev.impl.lang.fan.psi.impl.statements;

import javax.annotation.Nonnull;

import consulo.language.ast.ASTNode;
import consulo.language.psi.stub.IStubElementType;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.fandev.impl.lang.fan.psi.impl.FanBaseElementImpl;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nullable;

import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.StubElement;
import consulo.language.util.IncorrectOperationException;

/**
 * Date: Apr 28, 2009
 * Time: 5:22:06 PM
 *
 * @author Dror Bereznitsky
 */
public abstract class FanVariableBaseImpl<T extends StubElement> extends FanBaseElementImpl<T> implements FanVariable
{
	public FanVariableBaseImpl(final T t, @Nonnull final IStubElementType iStubElementType)
	{
		super(t, iStubElementType);
	}

	public FanVariableBaseImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Nonnull
	public FanType getType()
	{
		final FanType type = getDeclaredType();
		return type != null ? type : FanType.BOTTOM;
	}

	@Nullable
	public FanTypeElement getTypeElementFan()
	{
		return findChildByClass(FanTypeElement.class);
	}

	@Nullable
	public FanType getDeclaredType()
	{
		final FanTypeElement typeElement = getTypeElementFan();
		if(typeElement != null)
		{
			return typeElement.getType();
		}

		return null;
	}

	public PsiElement getNameIdentifier()
	{
		return findChildByType(FanElementTypes.ID_EXPR);
	}

	@Override
	public String getName()
	{
		final PsiElement identifier = getNameIdentifier();
		if(identifier != null)
		{
			return identifier.getText();
		}
		return null;
	}

	public PsiElement setName(@NonNls final String name) throws IncorrectOperationException
	{
		//TODO [Dror] implement
		return this;
	}
}
