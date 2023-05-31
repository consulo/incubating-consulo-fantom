/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fandev.lang.fan.parsing.expression.arithmetic;

import static org.fandev.lang.fan.FanElementTypes.CAST_EXPR;
import static org.fandev.lang.fan.FanElementTypes.EXPRESSION;
import static org.fandev.lang.fan.FanElementTypes.GROUPED_EXPR;
import static org.fandev.lang.fan.FanTokenTypes.LPAR;
import static org.fandev.lang.fan.FanTokenTypes.RPAR;
import static org.fandev.lang.fan.parsing.util.ParserUtils.getToken;
import static org.fandev.lang.fan.parsing.util.ParserUtils.removeNls;

import consulo.language.ast.TokenSet;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.parsing.expression.Expression;
import org.fandev.lang.fan.parsing.statements.Statement;
import org.fandev.lang.fan.parsing.types.TypeSpec;
import org.fandev.lang.fan.parsing.util.ParserUtils;
import consulo.language.parser.PsiBuilder;

/**
 * @author freds
 * @date Mar 1, 2009
 */
public class ParenExpression
{
	public static boolean parse(final PsiBuilder builder, final TokenSet stopper)
	{
		PsiBuilder.Marker marker = builder.mark();
		boolean res;
		if(getToken(builder, LPAR))
		{
			removeNls(builder);
			// First try cast expression then grouped expression
			res = parseCastExpression(builder, stopper);
			if(res)
			{
				marker.done(CAST_EXPR);
			}
			else
			{
				marker.rollbackTo();
				marker = builder.mark();
				// Eat the ( again
				ParserUtils.advanceNoNls(builder);
				res = Expression.parseExpr(builder, Statement.RPAR_STOPPER, EXPRESSION);
				getToken(builder, RPAR, FanBundle.message("rpar.expected"));
				if(res)
				{
					res = TermExpression.parseTermChainLoop(builder, stopper);
				}
				marker.done(GROUPED_EXPR);
			}
		}
		else
		{
			marker.drop();
			res = UnaryExpression.parse(builder, stopper);
		}
		return res;
	}

	private static boolean parseCastExpression(final PsiBuilder builder, final TokenSet stopper)
	{
		if(TypeSpec.parse(builder))
		{
			if(getToken(builder, RPAR, FanBundle.message("rpar.expected")))
			{
				return parse(builder, stopper);
			}
		}
		return false;
	}
}
