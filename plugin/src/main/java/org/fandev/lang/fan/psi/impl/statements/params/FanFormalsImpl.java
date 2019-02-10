package org.fandev.lang.fan.psi.impl.statements.params;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.api.statements.params.FanFormal;
import org.fandev.lang.fan.psi.api.statements.params.FanFormals;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import com.intellij.lang.ASTNode;

/**
 * Date: Aug 20, 2009
 * Time: 1:14:38 PM
 *
 * @author Dror Bereznitsky
 */
public class FanFormalsImpl extends FanBaseElementImpl implements FanFormals
{
	public FanFormalsImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	@Nonnull
	public FanFormal[] getParameters()
	{
		return findChildrenByClass(FanFormal.class);
	}

	@Override
	public int getParameterIndex(final FanFormal psiParameter)
	{
		final FanFormal[] parameters = getParameters();
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
