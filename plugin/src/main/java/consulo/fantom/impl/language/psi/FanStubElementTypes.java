package consulo.fantom.impl.language.psi;

import consulo.language.ast.IElementType;
import org.fandev.impl.lang.fan.FanElementTypes;

/**
 * @author VISTALL
 * @since 31/05/2023
 */
public interface FanStubElementTypes
{
	IElementType CLASS_DEFINITION = FanElementTypes.CLASS_DEFINITION;

	IElementType MIXIN_DEFINITION = FanElementTypes.MIXIN_DEFINITION;

	IElementType ENUM_DEFINITION = FanElementTypes.ENUM_DEFINITION;

	IElementType BUILDSCRIPT_DEFINITION = FanElementTypes.BUILDSCRIPT_DEFINITION;

	IElementType INHERITANCE_CLAUSE = FanElementTypes.INHERITANCE_CLAUSE;
}
