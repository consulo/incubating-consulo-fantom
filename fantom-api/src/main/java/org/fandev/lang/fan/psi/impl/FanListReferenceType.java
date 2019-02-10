package org.fandev.lang.fan.psi.impl;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanClassTypeElement;
import org.fandev.lang.fan.psi.api.types.FanListTypeElement;
import javax.annotation.Nonnull;

/**
 * Date: Jul 17, 2009
 * Time: 11:54:02 PM
 *
 * @author Dror Bereznitsky
 */
public class FanListReferenceType implements FanType
{
	private FanListTypeElement element;
	@Nonnull
	private final FanType myInnerType;

	public FanListReferenceType(final FanListTypeElement element, @Nonnull final FanType innerType)
	{
		this.element = element;
		myInnerType = innerType;
	}

	public FanTypeDefinition getListType()
	{
		return element.getListType();
	}

	public FanType getType()
	{
		return element.getType();
	}

	public FanClassTypeElement getTypeElement()
	{
		return element.getTypeElement();
	}

	@Override
	public String getPresentableText()
	{
		return myInnerType.getPresentableText() + "[]";
	}
}
