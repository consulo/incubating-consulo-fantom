package org.fandev.lang.fan.psi.impl.statements.expressions;

import javax.annotation.Nullable;

import org.fandev.lang.fan.psi.api.statements.expressions.FanSuperReferenceExpression;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.utils.FanUtil;
import com.intellij.lang.ASTNode;

/**
 * Date: Jul 19, 2009
 * Time: 10:24:08 PM
 *
 * @author Dror Bereznitsky
 */
public class FanSuperReferenceExpressionImpl extends FanExpressionImpl implements FanSuperReferenceExpression
{
	public FanSuperReferenceExpressionImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Nullable
	public FanTypeDefinition getReferencedType()
	{
		final FanTypeDefinition thisTypeDefinition = FanUtil.getContainingType(this);
		if(thisTypeDefinition != null)
		{
			return thisTypeDefinition.getSuperType();
		}
		return null;
	}
}
