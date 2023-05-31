package org.fandev.lang.fan.parsing.auxiliary.modifiers;

import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.util.ParserUtils;
import consulo.language.parser.PsiBuilder;

/**
 * @author Dror Bereznitsky
 * @date Jan 6, 2009 2:29:04 PM
 */
public class Modifier
{
	public static boolean parse(final PsiBuilder builder, final DeclarationType stmtType)
	{
		if(stmtType.getModifiersSet().contains(builder.getTokenType()))
		{
			ParserUtils.advanceNoNls(builder);
			return true;
		}
		return false;
	}
}
