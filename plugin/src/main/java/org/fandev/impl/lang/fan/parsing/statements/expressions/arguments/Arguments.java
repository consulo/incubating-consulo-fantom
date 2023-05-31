package org.fandev.impl.lang.fan.parsing.statements.expressions.arguments;

import static org.fandev.impl.lang.fan.FanElementTypes.ARGUMENT_EXPR;
import static org.fandev.impl.lang.fan.FanTokenTypes.COMMA;
import static org.fandev.impl.lang.fan.FanTokenTypes.LPAR;
import static org.fandev.impl.lang.fan.FanTokenTypes.RPAR;
import static org.fandev.impl.lang.fan.parsing.util.ParserUtils.advanceNoNls;
import static org.fandev.impl.lang.fan.parsing.util.ParserUtils.getToken;
import static org.fandev.impl.lang.fan.parsing.util.ParserUtils.removeNls;

import consulo.language.ast.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.impl.lang.fan.parsing.expression.Expression;
import consulo.language.parser.PsiBuilder;

/**
 * @author Dror Bereznitsky
 * @date Jan 14, 2009 1:29:26 PM
 */
public class Arguments
{
	public static final TokenSet ARGUMENTS_STOPPER = TokenSet.create(RPAR, COMMA);

	// <args> :=  [<expr> ("," <expr>)*]
	public static boolean parse(final PsiBuilder builder)
	{
		final PsiBuilder.Marker marker = builder.mark();
		if(LPAR == builder.getTokenType())
		{
			advanceNoNls(builder);
			while(!builder.eof() && RPAR != builder.getTokenType())
			{
				final boolean res = Expression.parseExpr(builder, ARGUMENTS_STOPPER, ARGUMENT_EXPR);
				removeNls(builder);
				if(!res || RPAR == builder.getTokenType())
				{
					break;
				}
				getToken(builder, COMMA, FanBundle.message("comma.expected"));
				removeNls(builder);
			}
			getToken(builder, RPAR, FanBundle.message("rpar.expected"));
			marker.done(FanElementTypes.ARGUMENT_LIST);
			return true;
		}
		marker.drop();
		return false;
	}
}
