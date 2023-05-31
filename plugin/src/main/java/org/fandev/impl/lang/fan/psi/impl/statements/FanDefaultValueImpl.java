package org.fandev.impl.lang.fan.psi.impl.statements;

import consulo.language.ast.ASTNode;
import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
import org.fandev.impl.lang.fan.psi.impl.FanBaseElementImpl;

/**
 * Date: Aug 24, 2009
 * Time: 11:50:03 PM
 *
 * @author Dror Bereznitsky
 */
public class FanDefaultValueImpl extends FanBaseElementImpl implements FanDefaultValue
{
	public FanDefaultValueImpl(final ASTNode astNode)
	{
		super(astNode);
	}
}
