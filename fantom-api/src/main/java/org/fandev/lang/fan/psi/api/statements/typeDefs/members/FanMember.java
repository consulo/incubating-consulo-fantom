package org.fandev.lang.fan.psi.api.statements.typeDefs.members;

import org.fandev.lang.fan.psi.api.modifiers.FanModifierListOwner;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

/**
 * @author VISTALL
 * @since 17.03.14
 */
public interface FanMember extends FanModifierListOwner
{
	FanTypeDefinition getContainingClass();
}
