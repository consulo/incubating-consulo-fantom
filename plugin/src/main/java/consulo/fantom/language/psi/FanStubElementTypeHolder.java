package consulo.fantom.language.psi;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.psi.stub.ObjectStubSerializerProvider;
import consulo.language.psi.stub.StubElementTypeHolder;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author VISTALL
 * @since 31/05/2023
 */
@ExtensionImpl
public class FanStubElementTypeHolder extends StubElementTypeHolder<FanStubElementTypes>
{
	@Nullable
	@Override
	public String getExternalIdPrefix()
	{
		return null;
	}

	@Nonnull
	@Override
	public List<ObjectStubSerializerProvider> loadSerializers()
	{
		return allFromStaticFields(FanStubElementTypes.class, Field::get);
	}
}
