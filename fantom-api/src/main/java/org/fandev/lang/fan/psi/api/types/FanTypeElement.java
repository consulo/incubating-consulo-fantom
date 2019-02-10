package org.fandev.lang.fan.psi.api.types;

import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.FanType;
import javax.annotation.Nonnull;

/**
 * Date: Jul 3, 2009
 * Time: 11:25:34 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanTypeElement extends FanElement
{
	@Nonnull
	FanType getType();
}
