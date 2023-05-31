package org.fandev.impl.lang.fan.structure.elements.itemsPresentations.impl;

import consulo.language.psi.PsiElement;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.impl.lang.fan.structure.elements.itemsPresentations.FanItemPresentation;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 7:29:09 PM
 */
public class FanMethodDefinitionItemPresentation extends FanItemPresentation
{
	public FanMethodDefinitionItemPresentation(final PsiElement myElement)
	{
		super(myElement);
	}

	public String getPresentableText()
	{
		return ((FanMethod) myElement).getName();
	}
}
