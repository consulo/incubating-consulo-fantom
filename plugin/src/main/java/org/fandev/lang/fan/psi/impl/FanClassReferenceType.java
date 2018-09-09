package org.fandev.lang.fan.psi.impl;

import org.fandev.lang.fan.psi.FanClassType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.search.GlobalSearchScope;

/**
 * Date: Mar 18, 2009
 * Time: 11:17:54 PM
 *
 * @author Dror Bereznitsky
 */
public class FanClassReferenceType implements FanClassType
{
	private final FanCodeReferenceElement myReferenceElement;

	public FanClassReferenceType(final FanCodeReferenceElement ref)
	{
		this.myReferenceElement = ref;
	}

	@Override
	public FanTypeDefinition resolve()
	{
		final ResolveResult[] results = multiResolve();
		if(results.length == 1)
		{
			final PsiElement only = results[0].getElement();
			return only instanceof FanTypeDefinition ? (FanTypeDefinition) only : null;
		}

		return null;
	}

	public FanTypeDefinition resolveFanType()
	{
		return resolve();
	}

	//reference resolve is cached
	private ResolveResult[] multiResolve()
	{
		return myReferenceElement.multiResolve(false);
	}

	public String getClassName()
	{
		return myReferenceElement.getReferenceName();
	}

	@Override
	public String getPresentableText()
	{
		return myReferenceElement.getReferenceName();
	}

	public String getCanonicalText()
	{
		return myReferenceElement.getReferenceName();
	}

	public String getInternalCanonicalText()
	{
		return getCanonicalText();
	}

	public boolean isValid()
	{
		return myReferenceElement.isValid();
	}

	public boolean equalsToText(@NonNls final String text)
	{
		return text.endsWith(getPresentableText()) && //optimization
				text.equals(getCanonicalText());
	}

	@NotNull
	public GlobalSearchScope getResolveScope()
	{
		return myReferenceElement.getResolveScope();
	}
}
