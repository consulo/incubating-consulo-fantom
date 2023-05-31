package org.fandev.impl.lang.fan;

import consulo.language.ast.IElementType;
import org.fandev.lang.fan.FanLanguage;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;

/**
 * @author Dror
 * @date Dec 13, 2008 11:26:41 PM
 */
public class FanElementType extends IElementType
{
	public FanElementType(@NonNls @Nonnull final String debugName)
	{
		super(debugName, FanLanguage.INSTANCE);
	}

	@Override
	@SuppressWarnings({"HardCodedStringLiteral"})
	public String toString()
	{
		return "FAN:" + super.toString();
	}
}
