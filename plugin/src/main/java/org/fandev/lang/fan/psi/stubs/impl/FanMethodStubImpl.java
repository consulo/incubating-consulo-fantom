package org.fandev.lang.fan.psi.stubs.impl;

import consulo.index.io.StringRef;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.stubs.FanMethodStub;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.StubElement;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 7:10:03 PM
 */
public class FanMethodStubImpl extends FanSlotStubImpl<FanMethod> implements FanMethodStub
{

	public FanMethodStubImpl(final StubElement parent, final StringRef name, final String[] facetNames)
	{
		super(parent, (IStubElementType) FanElementTypes.METHOD_DEFINITION, name, facetNames);
	}

}
