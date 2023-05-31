package org.fandev.impl.lang.fan.parsing.statements.typeDefinitions;

import static org.fandev.impl.lang.fan.FanElementTypes.INHERITANCE_CLAUSE;
import static org.fandev.impl.lang.fan.FanElementTypes.NONE;
import static org.fandev.impl.lang.fan.FanElementTypes.WRONGWAY;
import static org.fandev.impl.lang.fan.FanTokenTypes.COLON;
import static org.fandev.impl.lang.fan.FanTokenTypes.COMMA;

import consulo.language.parser.PsiBuilder;
import org.fandev.impl.lang.fan.parsing.util.ParserUtils;
import consulo.language.ast.IElementType;

/**
 * @author Dror Bereznitsky
 * @date Jan 6, 2009 4:59:02 PM
 */
public class InheritanceClause
{
	public static IElementType parse(final PsiBuilder builder)
	{
		final PsiBuilder.Marker sccMarker = builder.mark();

		if(!ParserUtils.getToken(builder, COLON))
		{
			sccMarker.rollbackTo();
			return NONE;
		}

		do
		{
			ParserUtils.removeNls(builder);
			if(!ReferenceElement.parseReferenceElement(builder))
			{
				sccMarker.rollbackTo();
				return WRONGWAY;
			}
			ParserUtils.removeNls(builder);
		}
		while(ParserUtils.getToken(builder, COMMA));

		sccMarker.done(INHERITANCE_CLAUSE);
		return INHERITANCE_CLAUSE;
	}
}
