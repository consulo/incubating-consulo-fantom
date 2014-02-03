package org.fandev.lang.fan.psi.api.statements.params;

import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiParameter;

/**
 * Date: Apr 29, 2009
 * Time: 11:09:40 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanParameter extends PsiParameter
{
	@Nullable
	public FanDefaultValue getDefaultValue();
}
