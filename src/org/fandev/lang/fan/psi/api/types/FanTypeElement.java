package org.fandev.lang.fan.psi.api.types;

import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.FanType;
import org.jetbrains.annotations.NotNull;

/**
 * Date: Jul 3, 2009
 * Time: 11:25:34 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanTypeElement extends FanElement
{
	@NotNull
	FanType getType();
}
