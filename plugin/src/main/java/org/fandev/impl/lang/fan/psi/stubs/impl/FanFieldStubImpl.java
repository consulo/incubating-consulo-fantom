package org.fandev.impl.lang.fan.psi.stubs.impl;

import consulo.index.io.StringRef;
import consulo.language.psi.stub.StubElement;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.impl.lang.fan.psi.stubs.FanFieldStub;
import consulo.language.psi.stub.IStubElementType;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 7:10:03 PM
 */
public class FanFieldStubImpl extends FanSlotStubImpl<FanField> implements FanFieldStub
{

	public FanFieldStubImpl(final StubElement parent, final StringRef name, final String[] facetNames)
	{
		super(parent, (IStubElementType) FanElementTypes.FIELD_DEFINITION, name, facetNames);
	}
}