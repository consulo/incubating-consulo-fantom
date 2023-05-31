package org.fandev.impl.lang.fan.psi.impl.statements.expressions;

import consulo.language.ast.ASTNode;
import org.fandev.impl.lang.fan.psi.api.statements.expressions.PodReferenceExpression;

/**
 * Date: Sep 12, 2009
 * Time: 11:19:47 PM
 *
 * @author Dror Bereznitsky
 */
public class PodReferenceExpressionImpl extends FanExpressionImpl implements PodReferenceExpression
{
	public PodReferenceExpressionImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	public String getPodName()
	{
		return getText();
	}
}
