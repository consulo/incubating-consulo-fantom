package org.fandev.impl.lang.fan.psi.stubs.impl;

import consulo.language.psi.stub.IStubElementType;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.impl.lang.fan.psi.api.statements.typeDefs.members.FanConstructor;
import org.fandev.impl.lang.fan.psi.stubs.FanConstructorStub;
import consulo.language.psi.stub.StubElement;
import consulo.index.io.StringRef;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 7:10:03 PM
 */
public class FanConstructorStubImpl extends FanSlotStubImpl<FanConstructor> implements FanConstructorStub
{

	public FanConstructorStubImpl(final StubElement parent, final StringRef name, final String[] facetNames)
	{
		super(parent, (IStubElementType) FanElementTypes.CTOR_DEFINITION, name, facetNames);
	}
}