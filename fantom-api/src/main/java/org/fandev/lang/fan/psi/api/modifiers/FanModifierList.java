package org.fandev.lang.fan.psi.api.modifiers;

import org.fandev.lang.fan.psi.FanElement;

/**
 * @author Dror Bereznitsky
 * @date Apr 2, 2009 2:41:03 PM
 */
public interface FanModifierList extends FanElement
{
	public boolean hasExplicitModifier(final String name);

	public boolean hasModifierProperty(final String modifier);
}
