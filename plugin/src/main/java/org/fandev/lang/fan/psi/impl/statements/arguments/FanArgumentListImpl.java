package org.fandev.lang.fan.psi.impl.statements.arguments;

import javax.annotation.Nonnull;

import consulo.language.ast.ASTNode;
import org.fandev.lang.fan.psi.api.statements.arguments.FanArgument;
import org.fandev.lang.fan.psi.api.statements.arguments.FanArgumentList;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;

/**
 * Date: Sep 15, 2009
 * Time: 10:35:51 PM
 *
 * @author Dror Bereznitsky
 */
public class FanArgumentListImpl extends FanBaseElementImpl implements FanArgumentList
{
	public FanArgumentListImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public String toString()
	{
		return "Arguments";
	}

	@Override
	public FanArgument[] getArguments()
	{
		return findChildrenByClass(FanArgument.class);
	}

	@Override
	public int indexOf(@Nonnull final FanArgument arg)
	{
		final FanArgument[] arguments = getArguments();
		for(int index = 0; index < arguments.length; index++)
		{
			if(arguments[index].equals(arg))
			{
				return index;
			}
		}
		return -1;
	}
}
