package org.fandev.impl.lang.fan.psi.stubs.elements;

import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.StubElement;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.impl.lang.fan.psi.impl.statements.typedefs.members.FanFieldImpl;
import org.fandev.impl.lang.fan.psi.stubs.FanFieldStub;
import org.fandev.impl.lang.fan.psi.stubs.impl.FanFieldStubImpl;
import javax.annotation.Nonnull;

import consulo.index.io.StringRef;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 6:56:12 PM
 */
public class FanFieldElementType extends FanSlotElementType<FanField, FanFieldStub>
{
	public FanFieldElementType(@Nonnull String debugName)
	{
		super(debugName);
	}

	public FanField createPsi(final FanFieldStub stub)
	{
		return new FanFieldImpl(stub, (IStubElementType) FanElementTypes.FIELD_DEFINITION);
	}

	protected FanFieldStub createStubImpl(final StubElement element, final StringRef name, final String[] facets)
	{
		return new FanFieldStubImpl(element, name, facets);
	}
}