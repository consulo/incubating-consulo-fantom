package org.fandev.lang.fan.psi.api.statements.arguments;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.FanElement;

/**
 * Date: Sep 17, 2009
 * Time: 11:47:43 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanArgument extends FanElement
{
	int getIndex();

	@Nonnull
	FanArgumentList getArgumentList();
}
