package org.fandev.lang.fan.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiQualifiedReference;

/**
 * @author Dror Bereznitsky
 * @date Feb 19, 2009 11:33:24 PM
 */
public interface FanReferenceElement extends FanElement, PsiQualifiedReference, PsiPolyVariantReference
{
	PsiElement getReferenceNameElement();

	@NotNull
	FanType[] getTypeArguments();
}
