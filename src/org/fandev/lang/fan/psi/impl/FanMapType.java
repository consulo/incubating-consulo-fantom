package org.fandev.lang.fan.psi.impl;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanMapTypeElement;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import com.intellij.psi.PsiElement;

/**
 * Date: Jul 21, 2009
 * Time: 11:44:07 PM
 *
 * @author Dror Bereznitsky
 */
public class FanMapType implements FanType
{
	private final FanTypeElement keyType;
	private final FanTypeElement valueType;
	private final String text;
	private FanMapTypeElement element;

	public FanMapType(final FanMapTypeElement element, final FanTypeElement keyType, final FanTypeElement valueType)
	{
		this.element = element;
		this.keyType = keyType;
		this.valueType = valueType;
		this.text = "[" + keyType.getType().getPresentableText() + ":" + valueType.getType().getPresentableText() + "]";
	}

	public FanTypeDefinition getMapType()
	{
		return element.getMapType();
	}

	public String getPresentableText()
	{
		return text;
	}

	@Override
	public PsiElement resolve()
	{
		return null;
	}

	public String getCanonicalText()
	{
		return text;
	}

	public String getInternalCanonicalText()
	{
		return text;
	}

	public boolean isValid()
	{
		return true;
	}
}
