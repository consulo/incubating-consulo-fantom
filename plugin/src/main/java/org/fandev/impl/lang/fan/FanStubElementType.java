package org.fandev.impl.lang.fan;

import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.StubElement;

import javax.annotation.Nonnull;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 5:33:46 PM
 */
public abstract class FanStubElementType<S extends StubElement, T extends PsiElement> extends IStubElementType<S, T>
{
	public FanStubElementType(@Nonnull final String debugName)
	{
		super(debugName, FanSupportLoader.FAN.getLanguage());
	}

	public String getExternalId()
	{
		return "fan." + super.toString();
	}
}
