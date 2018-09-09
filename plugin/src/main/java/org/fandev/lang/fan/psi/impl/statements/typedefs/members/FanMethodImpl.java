package org.fandev.lang.fan.psi.impl.statements.typedefs.members;

import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.stubs.FanMethodStub;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.psi.stubs.IStubElementType;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 6:57:41 PM
 */
public class FanMethodImpl extends FanMethodBaseImpl<FanMethodStub> implements FanMethod, StubBasedPsiElement<FanMethodStub>
{

	public FanMethodImpl(final FanMethodStub fanMethodStub, @NotNull final IStubElementType iStubElementType)
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
