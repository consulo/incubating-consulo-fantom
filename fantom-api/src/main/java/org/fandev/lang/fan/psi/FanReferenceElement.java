package org.fandev.lang.fan.psi;

import javax.annotation.Nonnull;

import consulo.language.psi.PsiPolyVariantReference;
import consulo.language.psi.PsiQualifiedReference;
import consulo.language.psi.PsiElement;

/**
 * @author Dror Bereznitsky
 * @date Feb 19, 2009 11:33:24 PM
 */
public interface FanReferenceElement extends FanElement, PsiQualifiedReference, PsiPolyVariantReference
{
	PsiElement getReferenceNameElement();

	@Nonnull
	FanType[] getTypeArguments();
}
