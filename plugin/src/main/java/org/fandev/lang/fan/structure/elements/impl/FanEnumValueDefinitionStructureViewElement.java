package org.fandev.lang.fan.structure.elements.impl;

import consulo.language.psi.PsiElement;
import consulo.navigation.ItemPresentation;
import org.fandev.lang.fan.structure.elements.FanStructureViewElement;
import org.fandev.lang.fan.structure.elements.itemsPresentations.impl.FanEnumValueDefinitionItemPresentation;
import consulo.fileEditor.structureView.tree.TreeElement;

/**
 * Date: Apr 1, 2009
 * Time: 12:01:26 AM
 *
 * @author Dror Bereznitsky
 */
public class FanEnumValueDefinitionStructureViewElement extends FanStructureViewElement
{
	PsiElement myElement;

	protected FanEnumValueDefinitionStructureViewElement(final PsiElement myElement)
	{
		super(myElement);
		this.myElement = myElement;
	}

	public ItemPresentation getPresentation()
	{
		return new FanEnumValueDefinitionItemPresentation(myElement);
	}

	public TreeElement[] getChildren()
	{
		return new TreeElement[0];
	}
}
