package org.fandev.impl.lang.fan.structure.elements.impl;

import consulo.language.psi.PsiElement;
import org.fandev.impl.lang.fan.structure.elements.FanStructureViewElement;
import org.fandev.impl.lang.fan.structure.elements.itemsPresentations.impl.FanMethodDefinitionItemPresentation;
import consulo.fileEditor.structureView.tree.TreeElement;
import consulo.navigation.ItemPresentation;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 7:27:34 PM
 */
public class FanMethodDefinitionStructureViewElement extends FanStructureViewElement
{
	PsiElement myElement;

	protected FanMethodDefinitionStructureViewElement(final PsiElement myElement)
	{
		super(myElement);
		this.myElement = myElement;
	}

	public ItemPresentation getPresentation()
	{
		return new FanMethodDefinitionItemPresentation(myElement);
	}

	public TreeElement[] getChildren()
	{
		return new TreeElement[0];
	}
}
