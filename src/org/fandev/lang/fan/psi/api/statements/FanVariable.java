package org.fandev.lang.fan.psi.api.statements;

import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.FanType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiNamedElement;

/**
 * Date: Apr 28, 2009
 * Time: 5:20:45 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanVariable extends FanElement, PsiNamedElement, PsiNameIdentifierOwner
{
	@NotNull
	FanType getType();

	@Nullable
	FanType getDeclaredType();
}
