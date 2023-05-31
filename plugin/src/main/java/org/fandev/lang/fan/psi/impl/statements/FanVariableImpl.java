package org.fandev.lang.fan.psi.impl.statements;

import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import javax.annotation.Nonnull;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.StubElement;

/**
 * Date: May 2, 2009
 * Time: 3:17:34 PM
 *
 * @author Dror Bereznitsky
 */
public class FanVariableImpl extends FanVariableBaseImpl implements FanVariable
{
	public FanVariableImpl(final StubElement stubElement, @Nonnull final IStubElementType iStubElementType)
	{
		super(stubElement, iStubElementType);
	}

	public FanVariableImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public PsiElement getNameIdentifier()
	{
		return findChildByType(FanElementTypes.NAME_ELEMENT);
	}

	@Override
	public FanTypeElement getTypeElementFan()
	{
		final FanTypeElement type = super.getTypeElementFan();
		if(type == null)
		{
			//TODO [Dror] handle type inference - probably any expression should have a getType method
		}
		return type;
	}
}
