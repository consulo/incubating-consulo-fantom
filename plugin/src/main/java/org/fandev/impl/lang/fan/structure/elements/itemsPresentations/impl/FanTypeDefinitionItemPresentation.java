package org.fandev.impl.lang.fan.structure.elements.itemsPresentations.impl;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.impl.lang.fan.structure.elements.itemsPresentations.FanItemPresentation;
import consulo.language.psi.PsiElement;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 4:55:09 PM
 */
public class FanTypeDefinitionItemPresentation extends FanItemPresentation
{
	public FanTypeDefinitionItemPresentation(final PsiElement myElement)
	{
		super(myElement);
	}

	public String getPresentableText()
	{
		return ((FanTypeDefinition) myElement).getName();
	}
}
