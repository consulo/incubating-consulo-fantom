package org.fandev.lang.fan.psi.api.statements.params;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.FanElement;

/**
 * Date: Aug 20, 2009
 * Time: 1:15:17 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanFormals extends FanElement
{
	@Nonnull
	FanFormal[] getParameters();

	int getParameterIndex(final FanFormal psiParameter);

	int getParametersCount();
}
