package org.fandev.impl.lang.fan.psi.impl;

import org.fandev.lang.fan.psi.FanElement;
import org.fandev.impl.lang.fan.psi.api.FanResolveResult;
import consulo.language.psi.PsiElement;

/**
 * @author Dror Bereznitsky
 * @date Feb 21, 2009 3:22:52 PM
 */
public class FanResolveResultImpl implements FanResolveResult
{
	private PsiElement myElement;
	private boolean myIsAccessible;
	private boolean myIsStaticsOK;

	private FanElement myCurrentFileResolveContext;

	public FanResolveResultImpl(final PsiElement element, final boolean isAccessible)
	{
		this(element, null, isAccessible, true);
	}

	public FanResolveResultImpl(final PsiElement element, final FanElement context, final boolean isAccessible,
			final boolean staticsOK)
	{
		myCurrentFileResolveContext = context;
		myElement = element;
		myIsAccessible = isAccessible;
		myIsStaticsOK = staticsOK;
	}

	public PsiElement getElement()
	{
		return myElement;
	}

	public boolean isValidResult()
	{
		return isAccessible();
	}

	public boolean isAccessible()
	{
		return myIsAccessible;
	}
}
