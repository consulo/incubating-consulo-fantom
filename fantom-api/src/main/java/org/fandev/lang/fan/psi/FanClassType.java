package org.fandev.lang.fan.psi;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

/**
 * @author VISTALL
 * @since 17.03.14
 */
public interface FanClassType extends FanType
{
	FanTypeDefinition resolve();
}
