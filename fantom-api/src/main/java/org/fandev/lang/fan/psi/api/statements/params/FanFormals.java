package org.fandev.lang.fan.psi.api.statements.params;

import org.fandev.lang.fan.psi.FanElement;
import org.jetbrains.annotations.NotNull;

/**
 * Date: Aug 20, 2009
 * Time: 1:15:17 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanFormals extends FanElement
{
	@NotNull
	FanFormal[] getParameters();

	int getParameterIndex(final FanFormal psiParameter);

	int getParametersCount();
}
