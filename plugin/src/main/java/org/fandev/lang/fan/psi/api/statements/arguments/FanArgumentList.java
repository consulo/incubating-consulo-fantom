package org.fandev.lang.fan.psi.api.statements.arguments;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.FanElement;

/**
 * Date: Sep 15, 2009
 * Time: 10:35:13 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanArgumentList extends FanElement
{
	FanArgument[] getArguments();

	int indexOf(@Nonnull final FanArgument arg);
}
