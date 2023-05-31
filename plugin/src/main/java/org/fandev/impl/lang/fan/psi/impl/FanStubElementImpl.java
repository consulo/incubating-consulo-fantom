package org.fandev.impl.lang.fan.psi.impl;

import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.StubElement;
import org.fandev.lang.fan.psi.FanElement;
import javax.annotation.Nonnull;
import consulo.language.impl.psi.stub.StubBasedPsiElementBase;
import consulo.language.ast.ASTNode;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 3:45:31 PM
 */
public class FanStubElementImpl<T extends StubElement> extends StubBasedPsiElementBase<T> implements FanElement
{
	public FanStubElementImpl(final T t, @Nonnull final IStubElementType iStubElementType)
	{
		super(t, iStubElementType);
	}

	public FanStubElementImpl(final ASTNode astNode)
	{
		super(astNode);
	}
}
