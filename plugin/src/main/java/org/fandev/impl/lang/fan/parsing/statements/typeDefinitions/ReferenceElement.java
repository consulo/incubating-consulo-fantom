package org.fandev.impl.lang.fan.parsing.statements.typeDefinitions;

import static org.fandev.impl.lang.fan.FanTokenTypes.IDENTIFIER_TOKENS_SET;

import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.impl.lang.fan.FanTokenTypes;
import consulo.language.parser.PsiBuilder;

/**
 * @author Dror Bereznitsky
 * @date Jan 6, 2009 10:42:23 PM
 */
public class ReferenceElement
{
	public static boolean parseReferenceElement(final PsiBuilder builder)
	{
		final PsiBuilder.Marker refelMark = builder.mark();
		boolean res = parse(builder);
		if(res && FanTokenTypes.COLON_COLON == builder.getTokenType())
		{
			// First one was pod name
			builder.advanceLexer();
			res = parse(builder);
		}
		if(res)
		{
			refelMark.done(FanElementTypes.REFERENCE_ELEMENT);
		}
		else
		{
			refelMark.drop();
		}
		return res;
	}

	public static boolean parse(final PsiBuilder builder)
	{
		if(IDENTIFIER_TOKENS_SET.contains(builder.getTokenType()))
		{
			builder.advanceLexer();
			return true;
		}
		return false;
	}
}
