package org.fandev.lang.fan.psi.api.statements.typeDefs.members;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.modifiers.FanModifierListOwner;
import org.fandev.lang.fan.psi.api.statements.FanParameterOwner;
import org.fandev.lang.fan.psi.api.statements.blocks.FanPsiCodeBlock;
import org.fandev.lang.fan.psi.api.statements.params.FanParameterList;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 6:54:59 PM
 */
public interface FanMethod extends FanSlot, FanParameterOwner, FanModifierListOwner
{
	FanMethod[] EMPTY_ARRAY = new FanMethod[0];

	@NotNull
	FanParameterList getParameterList();

	@NotNull
	FanType getReturnType();

	FanPsiCodeBlock getBody();

	void setBlock(final FanPsiCodeBlock newBlock);
}
