package org.fandev.impl.lang.fan.psi.stubs.elements;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanBuildScriptDefinition;
import org.fandev.impl.lang.fan.psi.impl.statements.typedefs.FanBuildScriptDefinitionImpl;
import org.fandev.impl.lang.fan.psi.stubs.FanTypeDefinitionStub;

/**
 * Date: July 29, 2009
 *
 * @author Fred Simon
 */
public class FanBuildScriptDefinitionElementType extends FanTypeDefinitionElementType<FanBuildScriptDefinition>
{
	public FanBuildScriptDefinitionElementType()
	{
		super("BuildScript definition");
	}

	public FanBuildScriptDefinition createPsi(final FanTypeDefinitionStub stub)
	{
		return new FanBuildScriptDefinitionImpl(stub);
	}
}
