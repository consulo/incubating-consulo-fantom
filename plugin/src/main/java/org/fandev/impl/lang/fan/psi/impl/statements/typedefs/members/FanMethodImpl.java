package org.fandev.impl.lang.fan.psi.impl.statements.typedefs.members;

import consulo.language.psi.StubBasedPsiElement;
import consulo.language.psi.stub.IStubElementType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.impl.lang.fan.psi.stubs.FanMethodStub;
import javax.annotation.Nonnull;
import consulo.language.ast.ASTNode;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 6:57:41 PM
 */
public class FanMethodImpl extends FanMethodBaseImpl<FanMethodStub> implements FanMethod, StubBasedPsiElement<FanMethodStub>
{

	public FanMethodImpl(final FanMethodStub fanMethodStub, @Nonnull final IStubElementType iStubElementType)
	{
		super(fanMethodStub, iStubElementType);
	}

	public FanMethodImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	public boolean isConstructor()
	{
		return false;
	}
}
