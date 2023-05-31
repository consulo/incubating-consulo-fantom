package org.fandev.lang.fan.psi.impl.types;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanMapTypeElement;
import org.fandev.lang.fan.psi.api.types.FanTypeElement;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanMapType;
import javax.annotation.Nonnull;
import consulo.language.ast.ASTNode;

/**
 * Date: Jul 21, 2009
 * Time: 11:48:52 PM
 *
 * @author Dror Bereznitsky
 */
public class FanMapTypeElementImpl extends FanBaseElementImpl implements FanMapTypeElement
{
	public FanMapTypeElementImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	@Nonnull
	public FanType getType()
	{
		final FanTypeElement[] keyValueTypes = findChildrenByClass(FanTypeElement.class);
		if(keyValueTypes.length == 2)
		{
			return new FanMapType(this, keyValueTypes[0], keyValueTypes[1]);
		}
		return null;
	}

	@Override
	public FanTypeDefinition getMapType()
	{
		return getFanTypeByName("Map");
	}
}
