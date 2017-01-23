package org.fandev.lang.fan.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.util.ArrayFactory;

/**
 * @author VISTALL
 * @since 04.02.14
 */
public interface FanType
{
	public static final FanType[] EMPTY_ARRAY = new FanType[0];

	public static ArrayFactory<FanType> ARRAY_FACTORY = new ArrayFactory<FanType>()
	{
		@NotNull
		@Override
		public FanType[] create(int count)
		{
			return count == 0 ? EMPTY_ARRAY : new FanType[count];
		}
	};

	FanType BOTTOM = new FanType()
	{
		@Override
		public String getPresentableText()
		{
			return "bottom";
		}
	};

	FanType VOID = new FanType()
	{
		@Override
		public String getPresentableText()
		{
			return "void";
		}
	};

	String getPresentableText();
}
