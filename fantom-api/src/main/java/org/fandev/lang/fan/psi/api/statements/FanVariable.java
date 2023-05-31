package org.fandev.lang.fan.psi.api.statements;

import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.FanType;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import consulo.language.psi.PsiNameIdentifierOwner;
import consulo.language.psi.PsiNamedElement;

/**
 * Date: Apr 28, 2009
 * Time: 5:20:45 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanVariable extends FanElement, PsiNamedElement, PsiNameIdentifierOwner
{
	@Nonnull
	FanType getType();

	@Nullable
	FanType getDeclaredType();
}
