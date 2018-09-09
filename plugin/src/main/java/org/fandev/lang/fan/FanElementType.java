package org.fandev.lang.fan;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

/**
 * @author Dror
 * @date Dec 13, 2008 11:26:41 PM
 */
public class FanElementType extends IElementType
{
	public FanElementType(@NonNls @NotNull final String debugName)
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
