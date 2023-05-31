package org.fandev.impl.lang.fan.psi.impl.types;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.types.FanClassTypeElement;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.impl.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.impl.lang.fan.psi.impl.FanClassReferenceType;
import consulo.language.ast.ASTNode;

/**
 * Date: Jul 3, 2009
 * Time: 11:26:44 PM
 *
 * @author Dror Bereznitsky
 */
public class FanClassTypeElementImpl extends FanBaseElementImpl implements FanClassTypeElement
{
	public FanClassTypeElementImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public String toString()
	{
		return "Class Type element";
	}

	@Override
	@Nonnull
	public FanCodeReferenceElement getReferenceElement()
	{
		return findChildByClass(FanCodeReferenceElement.class);
	}

	@Override
	@Nonnull
	public FanType getType()
	{
		return new FanClassReferenceType(getReferenceElement());
	}
}
