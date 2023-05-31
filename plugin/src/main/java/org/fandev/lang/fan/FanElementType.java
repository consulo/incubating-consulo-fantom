package org.fandev.lang.fan;

import javax.annotation.Nonnull;

import consulo.language.ast.IElementType;
import org.jetbrains.annotations.NonNls;

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
