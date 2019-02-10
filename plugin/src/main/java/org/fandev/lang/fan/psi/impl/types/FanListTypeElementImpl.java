package org.fandev.lang.fan.psi.impl.types;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanClassTypeElement;
import org.fandev.lang.fan.psi.api.types.FanListTypeElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanListReferenceType;
import com.intellij.lang.ASTNode;

/**
 * Date: Jul 17, 2009
 * Time: 11:45:15 PM
 *
 * @author Dror Bereznitsky
 */
public class FanListTypeElementImpl extends FanBaseElementImpl implements FanListTypeElement
{
	public FanListTypeElementImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	@Nonnull
	public FanType getType()
	{
		final FanClassTypeElement fanTypeElem = getTypeElement();
		return new FanListReferenceType(this, fanTypeElem.getType());
	}

	@Override
	@Nonnull
	public FanClassTypeElement getTypeElement()
	{
		return findChildByClass(FanClassTypeElement.class);
	}

	@Override
	public FanTypeDefinition getListType()
	{
		return getFanTypeByName("List");
	}
}
