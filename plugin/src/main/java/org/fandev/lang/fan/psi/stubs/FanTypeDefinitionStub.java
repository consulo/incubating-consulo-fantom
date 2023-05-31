package org.fandev.lang.fan.psi.stubs;

import consulo.language.psi.stub.NamedStub;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 5:40:42 PM
 */
public interface FanTypeDefinitionStub extends NamedStub<FanTypeDefinition>
{
	String getPodName();
}
