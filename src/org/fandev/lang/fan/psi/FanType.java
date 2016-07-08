package org.fandev.lang.fan.psi;

import consulo.lombok.annotations.ArrayFactoryFields;

/**
 * @author VISTALL
 * @since 04.02.14
 */
@ArrayFactoryFields
public interface FanType
{
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
