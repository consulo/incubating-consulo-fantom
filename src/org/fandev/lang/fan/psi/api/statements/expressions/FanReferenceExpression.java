package org.fandev.lang.fan.psi.api.statements.expressions;

import org.fandev.lang.fan.psi.FanReferenceElement;
import org.fandev.lang.fan.psi.api.FanResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiNamedElement;

/**
 * Date: Jun 24, 2009
 * Time: 11:47:29 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanReferenceExpression extends FanExpression, FanReferenceElement, PsiNamedElement
{
	@Nullable
	FanExpression getQualifierExpression();

	@NotNull
	FanResolveResult[] getSameNameVariants();
}
