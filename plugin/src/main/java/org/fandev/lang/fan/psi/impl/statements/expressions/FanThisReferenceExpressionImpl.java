package org.fandev.lang.fan.psi.impl.statements.expressions;

import org.fandev.lang.fan.psi.api.statements.expressions.FanThisReferenceExpression;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.utils.FanUtil;
import consulo.language.ast.ASTNode;

/**
 * Date: Jul 16, 2009
 * Time: 11:06:40 PM
 *
 * @author Dror Bereznitsky
 */
public class FanThisReferenceExpressionImpl extends FanExpressionImpl implements FanThisReferenceExpression
{
	public FanThisReferenceExpressionImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	public FanTypeDefinition getReferencedType()
	{
		return FanUtil.getContainingType(this);
	}
}
