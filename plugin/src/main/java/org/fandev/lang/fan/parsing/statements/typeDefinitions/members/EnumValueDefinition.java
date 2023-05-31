package org.fandev.lang.fan.parsing.statements.typeDefinitions.members;

import static org.fandev.lang.fan.FanTokenTypes.IDENTIFIER_TOKENS_SET;
import static org.fandev.lang.fan.FanTokenTypes.LPAR;

import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.parsing.statements.expressions.arguments.Arguments;
import consulo.language.parser.PsiBuilder;

/**
 * Date: Mar 28, 2009
 * Time: 4:02:44 PM
 *
 * @author Dror Bereznitsky
 */
public class EnumValueDefinition
{
	public static boolean parse(final PsiBuilder builder)
	{
		final PsiBuilder.Marker marker = builder.mark();

		// Enum value
		if(!IDENTIFIER_TOKENS_SET.contains(builder.getTokenType()))
		{
			marker.drop();
			return false;
		}
		final PsiBuilder.Marker idMark = builder.mark();
		builder.advanceLexer();
		idMark.done(FanElementTypes.NAME_ELEMENT);

		// possible constructor arguments
		if(LPAR.equals(builder.getTokenType()))
		{
			if(Arguments.parse(builder))
			{
				marker.done(FanElementTypes.ENUM_VALUE);
				return true;
			}
			else
			{
				marker.error(FanBundle.message("argument.expected"));
			}
		}
		marker.done(FanElementTypes.ENUM_VALUE);
		return true;
	}
}
