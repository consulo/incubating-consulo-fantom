package org.fandev.impl.lang.fan.psi.impl.statements.expressions;

import consulo.language.ast.ASTNode;
import org.fandev.impl.lang.fan.psi.api.statements.expressions.FanTermExpression;

/**
 * Date: Aug 24, 2009
 * Time: 11:58:51 PM
 *
 * @author Dror Bereznitsky
 */
public class FanTermExpressionImpl extends FanExpressionImpl implements FanTermExpression
{
	public FanTermExpressionImpl(final ASTNode astNode)
	{
		super(astNode);
	}
}
