package org.fandev.lang.fan.psi.api.statements.expressions;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.jetbrains.annotations.Nullable;

/**
 * Date: Sep 12, 2009
 * Time: 6:35:50 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanTypeReferenceExpression
{
	@Nullable
	FanTypeDefinition getReferencedType();
}
