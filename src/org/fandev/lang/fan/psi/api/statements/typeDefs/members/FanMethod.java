package org.fandev.lang.fan.psi.api.statements.typeDefs.members;

import org.fandev.lang.fan.psi.api.statements.FanParameterOwner;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiMethod;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 6:54:59 PM
 */
public interface FanMethod extends FanSlot, FanParameterOwner, PsiMethod
{
	FanMethod[] EMPTY_ARRAY = new FanMethod[0];

	void setBlock(final PsiCodeBlock newBlock);
}
