package org.fandev.impl.lang.fan.psi.stubs.elements;

import java.io.IOException;

import org.fandev.impl.lang.fan.FanStubElementType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.impl.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.fandev.impl.lang.fan.psi.stubs.impl.FanTypeDefinitionStubImpl;
import org.fandev.lang.fan.psi.stubs.index.FanShortClassNameIndex;
import javax.annotation.Nonnull;
import consulo.language.psi.stub.IndexSink;
import consulo.language.psi.stub.StubElement;
import consulo.language.psi.stub.StubInputStream;
import consulo.language.psi.stub.StubOutputStream;
import consulo.index.io.StringRef;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 12:25:41 AM
 */
public abstract class FanTypeDefinitionElementType<TypeDef extends FanTypeDefinition> extends FanStubElementType<FanTypeDefinitionStub, TypeDef>
{
	public FanTypeDefinitionElementType(@Nonnull final String debugName)
	{
		super(debugName);
	}

	public FanTypeDefinitionStub createStub(final TypeDef psi, final StubElement parentStub)
	{
		return new FanTypeDefinitionStubImpl(parentStub, this, StringRef.fromString(psi.getName()), StringRef.fromString(psi.getPodName()));
	}

	public void serialize(final FanTypeDefinitionStub stub, final StubOutputStream dataStream) throws IOException
	{
		dataStream.writeName(stub.getName());
		dataStream.writeName(stub.getPodName());
	}

	public FanTypeDefinitionStub deserialize(final StubInputStream dataStream, final StubElement parentStub) throws IOException
	{
		final StringRef name = dataStream.readName();
		final StringRef podName = dataStream.readName();
		return new FanTypeDefinitionStubImpl(parentStub, this, name, podName);
	}

	public void indexStub(final FanTypeDefinitionStub stub, final IndexSink sink)
	{
		final String shortName = stub.getName();
		if(shortName != null)
		{
			sink.occurrence(FanShortClassNameIndex.KEY, shortName);
		}
	}
}
