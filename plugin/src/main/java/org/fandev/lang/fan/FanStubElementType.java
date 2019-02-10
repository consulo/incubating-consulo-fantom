package org.fandev.lang.fan;

import javax.annotation.Nonnull;

import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;

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
