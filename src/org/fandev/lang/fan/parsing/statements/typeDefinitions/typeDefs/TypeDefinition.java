package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;

import static org.fandev.lang.fan.FanElementTypes.BUILDSCRIPT_DEFINITION;
import static org.fandev.lang.fan.FanElementTypes.CLASS_DEFINITION;
import static org.fandev.lang.fan.FanElementTypes.ENUM_DEFINITION;
import static org.fandev.lang.fan.FanElementTypes.MIXIN_DEFINITION;
import static org.fandev.lang.fan.FanTokenTypes.CLASS_KEYWORD;
import static org.fandev.lang.fan.FanTokenTypes.ENUM_KEYWORD;
import static org.fandev.lang.fan.FanTokenTypes.LBRACE;
import static org.fandev.lang.fan.FanTokenTypes.MIXIN_KEYWORD;
import static org.fandev.lang.fan.FanTokenTypes.POD_KEYWORD;

import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.parsing.auxiliary.facets.Facet;
import org.fandev.lang.fan.parsing.util.ParserUtils;
import com.intellij.lang.PsiBuilder;

/**
 * @author Dror Bereznitsky
 * @date Jan 6, 2009 2:16:52 PM
 */
public class TypeDefinition
{
	public static boolean parse(final PsiBuilder builder)
	{
		boolean res;
		final PsiBuilder.Marker tdMarker = builder.mark();
		Facet.parse(builder);
		ParserUtils.removeNls(builder);
		if(ParserUtils.lookAheadForElement(builder, CLASS_KEYWORD, LBRACE))
		{
			res = ClassDefinition.parse(builder);
			tdMarker.done(CLASS_DEFINITION);
		}
		else if(ParserUtils.lookAheadForElement(builder, POD_KEYWORD, LBRACE))
		{
			// Should be possible only in build.fan files
			res = PodDefinition.parse(builder);
			tdMarker.done(BUILDSCRIPT_DEFINITION);
		}
		else if(ParserUtils.lookAheadForElement(builder, ENUM_KEYWORD, LBRACE))
		{
			res = EnumDefinition.parse(builder);
			tdMarker.done(ENUM_DEFINITION);
		}
		else if(ParserUtils.lookAheadForElement(builder, MIXIN_KEYWORD, LBRACE))
		{
			res = MixinDefinition.parse(builder);
			tdMarker.done(MIXIN_DEFINITION);
		}
		else
		{
			res = false;
			tdMarker.error(FanBundle.message("typedef.expected"));
		}
		ParserUtils.removeNls(builder);
		return res;
	}
}
