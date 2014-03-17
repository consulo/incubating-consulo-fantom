package org.fandev.lang.fan.psi.api.modifiers;

import org.fandev.lang.fan.psi.FanElement;
import org.jetbrains.annotations.Nullable;

/**
 * @author VISTALL
 * @since 17.03.14
 */
public interface FanModifierListOwner extends FanElement
{
	@Nullable
	FanModifierList getModifierList();

	boolean hasExplicitModifier(final String name);

	boolean hasModifierProperty(final String modifier);
}
