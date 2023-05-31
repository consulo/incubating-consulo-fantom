package org.fandev.lang.fan.psi;

import javax.annotation.Nonnull;

import consulo.util.collection.ArrayFactory;

/**
 * @author VISTALL
 * @since 04.02.14
 */
public interface FanType
{
	public static final FanType[] EMPTY_ARRAY = new FanType[0];

	public static ArrayFactory<FanType> ARRAY_FACTORY = new ArrayFactory<FanType>()
	{
		@Nonnull
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
