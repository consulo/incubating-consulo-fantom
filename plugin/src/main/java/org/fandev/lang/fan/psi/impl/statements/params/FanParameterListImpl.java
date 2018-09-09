package org.fandev.lang.fan.psi.impl.statements.params;

import org.fandev.lang.fan.psi.api.statements.params.FanParameter;
import org.fandev.lang.fan.psi.api.statements.params.FanParameterList;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

/**
 * Date: Jul 8, 2009
 * Time: 11:40:03 PM
 *
 * @author Dror Bereznitsky
 */
public class FanParameterListImpl extends FanBaseElementImpl implements FanParameterList
{
	public FanParameterListImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	@NotNull
	public FanParameter[] getParameters()
	{
		return findChildrenByClass(FanParameter.class);
	}

	@Override
	public int getParameterIndex(final FanParameter psiParameter)
	{
		final FanParameter[] parameters = getParameters();
		for(int i = 0; i < parameters.length; i++)
		{
			if(parameters[i].equals(psiParameter))
			{
				return i;
			}
		}

		return -1;
	}

	@Override
	public int getParametersCount()
	{
		return getParameters().length;
	}
}
