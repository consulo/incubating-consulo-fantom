package org.fandev.lang.fan.psi.api.statements.params;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
import org.fandev.lang.fan.psi.api.statements.FanVariable;

import javax.annotation.Nullable;
import com.intellij.psi.PsiElement;

/**
 * Date: Apr 29, 2009
 * Time: 11:09:40 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanParameter extends FanVariable
{
	@Nullable
	public FanDefaultValue getDefaultValue();

	@Nonnull
	PsiElement getDeclarationScope();
}
