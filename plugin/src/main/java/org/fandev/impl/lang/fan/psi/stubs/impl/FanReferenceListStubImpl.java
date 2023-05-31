package org.fandev.impl.lang.fan.psi.stubs.impl;

import consulo.language.psi.stub.StubBase;
import consulo.language.psi.stub.StubElement;
import org.fandev.impl.lang.fan.psi.api.statements.typeDefs.FanReferenceList;
import org.fandev.impl.lang.fan.psi.stubs.FanReferenceListStub;
import consulo.language.psi.stub.IStubElementType;

/**
 * Created by IntelliJ IDEA.
 * User: Dror
 * Date: Mar 20, 2009
 * Time: 4:17:33 PM
 */
public class FanReferenceListStubImpl extends StubBase<FanReferenceList> implements FanReferenceListStub
{
	private final String[] myRefNames;

	public FanReferenceListStubImpl(final StubElement parent, final IStubElementType elementType, final String[] refNames)
	{
		super(parent, elementType);
		myRefNames = refNames;
	}

	public String[] getBaseClasses()
	{
		return myRefNames;
	}
}
