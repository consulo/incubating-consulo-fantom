package org.fandev.lang.fan.psi.impl;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;

/**
 * Date: Jul 19, 2009
 * Time: 11:25:33 PM
 *
 * @author Dror Bereznitsky
 */
public class FanFuncType implements FanType
{
	private FanFuncTypeElement element;

	// TODO [Dror] maybe find something better as the constructor argument
	public FanFuncType(final FanFuncTypeElement element)
	{
		this.element = element;
	}

	public FanType getReturnType()
	{
		return element.getReturnType().getType();
	}

	public FanTypeDefinition getFuncType()
	{
		return element.getFuncType();
	}

	public String getPresentableText()
	{
		return element.getText();
	}

	public String getCanonicalText()
	{
		return element.getText();
	}

	public String getInternalCanonicalText()
	{
		return element.getText();
	}

	public boolean isValid()
	{
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
