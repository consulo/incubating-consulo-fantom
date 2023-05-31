package org.fandev.lang.fan.psi.impl.statements.expressions;

import consulo.language.ast.ASTNode;
import org.fandev.lang.fan.psi.api.statements.expressions.FanIndexExpression;

/**
 * Date: Sep 2, 2009
 * Time: 12:23:12 AM
 *
 * @author Dror Bereznitsky
 */
public class FanIndexExpressionImpl extends FanExpressionImpl implements FanIndexExpression
{
	public FanIndexExpressionImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	public int getIndex()
	{
		return Integer.valueOf(getText());
	}
}
