package org.fandev.impl.lang.fan.psi.impl.statements.expressions;

import consulo.language.ast.ASTNode;
import org.fandev.impl.lang.fan.psi.api.statements.expressions.FanUnaryExpression;

/**
 * Date: Aug 24, 2009
 * Time: 11:59:36 PM
 *
 * @author Dror Bereznitsky
 */
public class FanUnaryExpressionImpl extends FanExpressionImpl implements FanUnaryExpression
{
	public FanUnaryExpressionImpl(final ASTNode astNode)
	{
		super(astNode);
	}
}
