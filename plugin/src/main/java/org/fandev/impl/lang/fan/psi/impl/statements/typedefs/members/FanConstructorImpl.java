package org.fandev.impl.lang.fan.psi.impl.statements.typedefs.members;

import org.fandev.impl.lang.fan.psi.api.statements.typeDefs.members.FanConstructor;
import org.fandev.impl.lang.fan.psi.stubs.FanConstructorStub;
import javax.annotation.Nonnull;
import consulo.language.ast.ASTNode;
import consulo.language.psi.StubBasedPsiElement;
import consulo.language.psi.stub.IStubElementType;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 6:57:41 PM
 */
public class FanConstructorImpl extends FanMethodBaseImpl<FanConstructorStub> implements FanConstructor, StubBasedPsiElement<FanConstructorStub>
{

	public FanConstructorImpl(final FanConstructorStub fanMethodStub, @Nonnull final IStubElementType iStubElementType)
	{
		super(fanMethodStub, iStubElementType);
	}

	public FanConstructorImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	public boolean isConstructor()
	{
		return true;
	}
}