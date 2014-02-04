package org.fandev.lang.fan.psi.api.statements.params;

import org.fandev.lang.fan.psi.FanElement;
import org.jetbrains.annotations.NotNull;

/**
 * Date: Jul 8, 2009
 * Time: 11:39:03 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanParameterList extends FanElement
{
	@NotNull
	FanParameter[] getParameters();

	int getParameterIndex(final FanParameter psiParameter);

	int getParametersCount();
}
