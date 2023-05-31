package org.fandev.impl.lang.fan.psi.api.statements.expressions;

import consulo.language.psi.PsiNamedElement;
import org.fandev.impl.lang.fan.psi.api.FanResolveResult;
import org.fandev.lang.fan.psi.FanReferenceElement;
import org.fandev.lang.fan.psi.api.statements.expressions.FanExpression;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

	@Nonnull
	FanResolveResult[] getSameNameVariants();
}
