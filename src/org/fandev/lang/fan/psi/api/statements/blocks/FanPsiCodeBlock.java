package org.fandev.lang.fan.psi.api.statements.blocks;

import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanStatement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

/**
 * Date: Oct 1, 2009
 * Time: 12:32:29 AM
 *
 * @author Dror Bereznitsky
 */
public interface FanPsiCodeBlock extends FanElement
{
	@NotNull
	FanStatement[] getStatements();

	public PsiElement getLeftBrace();

	public PsiElement getRightBrace();
}
