package org.fandev.lang.fan.psi.api.statements.blocks;

import org.fandev.lang.fan.psi.FanElement;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;

/**
 * Date: Oct 1, 2009
 * Time: 12:32:29 AM
 *
 * @author Dror Bereznitsky
 */
public interface FanPsiCodeBlock extends FanElement, PsiCodeBlock
{
	public PsiElement getLeftBrace();

	public PsiElement getRightBrace();
}
