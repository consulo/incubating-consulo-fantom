package org.fandev.impl.lang.fan.psi.stubs.elements;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.impl.lang.fan.psi.impl.statements.typedefs.FanEnumDefinitionImpl;
import org.fandev.impl.lang.fan.psi.stubs.FanTypeDefinitionStub;

/**
 * Date: Mar 28, 2009
 * Time: 3:35:56 PM
 *
 * @author Dror Bereznitsky
 */
public class FanEnumDefinitionElementType extends FanTypeDefinitionElementType<FanEnumDefinition>
{
	public FanEnumDefinitionElementType()
	{
		super("enum definition");
	}

	public FanEnumDefinition createPsi(final FanTypeDefinitionStub stub)
	{
		return new FanEnumDefinitionImpl(stub);
	}
}
