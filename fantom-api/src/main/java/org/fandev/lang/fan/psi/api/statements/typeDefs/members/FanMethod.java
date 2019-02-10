package org.fandev.lang.fan.psi.api.statements.typeDefs.members;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.modifiers.FanModifierListOwner;
import org.fandev.lang.fan.psi.api.statements.FanParameterOwner;
import org.fandev.lang.fan.psi.api.statements.blocks.FanPsiCodeBlock;
import org.fandev.lang.fan.psi.api.statements.params.FanParameterList;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 6:54:59 PM
 */
public interface FanMethod extends FanSlot, FanParameterOwner, FanModifierListOwner
{
	FanMethod[] EMPTY_ARRAY = new FanMethod[0];

	@Nonnull
	FanParameterList getParameterList();

	@Nonnull
	FanType getReturnType();

	FanPsiCodeBlock getBody();

	void setBlock(final FanPsiCodeBlock newBlock);
}
