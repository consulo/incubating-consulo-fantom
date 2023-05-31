package org.fandev.lang.fan.psi.impl.types;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.params.FanFormals;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanFuncType;
import javax.annotation.Nonnull;
import consulo.language.ast.ASTNode;

/**
 * Date: Jul 19, 2009
 * Time: 11:21:23 PM
 *
 * @author Dror Bereznitsky
 */
public class FanFuncTypeElementImpl extends FanBaseElementImpl implements FanFuncTypeElement
{
	public FanFuncTypeElementImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	@Nonnull
	public FanType getType()
	{
		return new FanFuncType(this);
	}

	@Override
	public FanFormals getFormals()
	{
		return findChildByClass(FanFormals.class);
	}

	@Override
	public FanTypeElement getReturnType()
	{
		final FanTypeElement returnType = findChildByClass(FanTypeElement.class);
		if(returnType == null)
		{
			//TODO [Dror] return Void type
		}
		return returnType;
	}

	@Override
	public FanTypeDefinition getFuncType()
	{
		return getFanTypeByName("Func");
	}
}
