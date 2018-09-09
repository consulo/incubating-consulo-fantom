package org.fandev.lang.fan.psi.api.statements.expressions;

import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
import com.intellij.psi.PsiElement;

/**
 * Date: Aug 18, 2009
 * Time: 12:05:07 AM
 *
 * @author Dror Bereznitsky
 */
public interface FanClosureExpression extends PsiElement
{
	FanFuncTypeElement getFunction();
}
