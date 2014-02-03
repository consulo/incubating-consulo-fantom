package org.fandev.lang.fan.parsing.auxiliary.facets;

import static org.fandev.lang.fan.FanTokenTypes.AT;
import static org.fandev.lang.fan.FanTokenTypes.EOS;
import static org.fandev.lang.fan.FanTokenTypes.SEPARATOR;

import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.util.ParserUtils;
import com.intellij.lang.PsiBuilder;

/**
 * @author Dror Bereznitsky
 * @date Jan 14, 2009 11:15:08 PM
 */
public class Facet
{
	// Facets are defined as "@name=value" prefixed before a type or slot definition
	// @<id>[=<expr>]<eos>
	public static boolean parse(final PsiBuilder builder)
	{
		ParserUtils.removeNls(builder);
		while(AT.equals(builder.getTokenType()))
		{
			final PsiBuilder.Marker facetMarker = builder.mark();
			builder.advanceLexer();
			// Annotation itself
			if(!ParserUtils.parseName(builder))
			{
				facetMarker.drop();
				return false;
			}
			if(FanTokenTypes.EQ.equals(builder.getTokenType()))
			{
				ParserUtils.advanceNoNls(builder);
				Expression.parseExpr(builder, EOS, FanElementTypes.FACET_VALUE);
			}
			facetMarker.done(FanElementTypes.FACET);
			ParserUtils.removeStoppers(builder, SEPARATOR, SEPARATOR);
		}
		return true;
	}
}
