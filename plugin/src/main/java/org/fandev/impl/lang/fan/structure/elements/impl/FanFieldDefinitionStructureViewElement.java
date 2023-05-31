package org.fandev.impl.lang.fan.structure.elements.impl;

import consulo.fileEditor.structureView.tree.TreeElement;
import org.fandev.impl.lang.fan.structure.elements.FanStructureViewElement;
import org.fandev.impl.lang.fan.structure.elements.itemsPresentations.impl.FanFieldDefinitionItemPresentation;
import consulo.navigation.ItemPresentation;
import consulo.language.psi.PsiElement;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 7:27:34 PM
 */
public class FanFieldDefinitionStructureViewElement extends FanStructureViewElement
{
	PsiElement myElement;

	protected FanFieldDefinitionStructureViewElement(final PsiElement myElement)
	{
		super(myElement);
		this.myElement = myElement;
	}

	public ItemPresentation getPresentation()
	{
		return new FanFieldDefinitionItemPresentation(myElement);
	}

	public TreeElement[] getChildren()
	{
		return new TreeElement[0];
	}
}