package org.fandev.lang.fan.parsing.statements.typeDefinitions;

import static org.fandev.lang.fan.FanElementTypes.INHERITANCE_CLAUSE;
import static org.fandev.lang.fan.FanElementTypes.NONE;
import static org.fandev.lang.fan.FanElementTypes.WRONGWAY;
import static org.fandev.lang.fan.FanTokenTypes.COLON;
import static org.fandev.lang.fan.FanTokenTypes.COMMA;

import org.fandev.lang.fan.parsing.util.ParserUtils;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

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
