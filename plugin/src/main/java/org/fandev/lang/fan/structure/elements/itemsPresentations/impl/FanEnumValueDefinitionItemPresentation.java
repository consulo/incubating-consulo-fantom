package org.fandev.lang.fan.structure.elements.itemsPresentations.impl;

import org.fandev.icons.Icons;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
import org.fandev.lang.fan.structure.elements.itemsPresentations.FanItemPresentation;
import consulo.language.psi.PsiElement;
import consulo.ui.image.Image;

/**
 * Date: Apr 1, 2009
 * Time: 12:02:36 AM
 *
 * @author Dror Bereznitsky
 */
public class FanEnumValueDefinitionItemPresentation extends FanItemPresentation
{
	public FanEnumValueDefinitionItemPresentation(PsiElement myElement)
	{
		super(myElement);
	}

	public String getPresentableText()
	{
		final FanEnumValue element = (FanEnumValue) myElement;
		return element.getName() + ":" + element.getContainingClass().getName();
	}

	@Override
	public Image getIcon(final boolean open)
	{
		return Icons.FIELD;
	}
}
