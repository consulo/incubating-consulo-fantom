package org.fandev.lang.fan.structure.elements.impl;

import java.util.ArrayList;
import java.util.List;

import consulo.fileEditor.structureView.tree.TreeElement;
import consulo.language.psi.PsiElement;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.impl.FanFileImpl;
import org.fandev.lang.fan.structure.elements.FanStructureViewElement;
import org.fandev.lang.fan.structure.elements.itemsPresentations.impl.FanFileItemPresentation;
import consulo.navigation.ItemPresentation;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 4:34:34 PM
 */
public class FanFileStructureViewElement extends FanStructureViewElement
{
	public FanFileStructureViewElement(final PsiElement myElement)
	{
		super(myElement);
	}

	public ItemPresentation getPresentation()
	{
		return new FanFileItemPresentation((FanFileImpl) myElement);
	}

	public TreeElement[] getChildren()
	{
		final List<FanStructureViewElement> children = new ArrayList<FanStructureViewElement>();

		for(final PsiElement element : myElement.getChildren())
		{
			if(element instanceof FanTypeDefinition)
			{
				children.add(new FanTypeDefinitionStructureViewElement((FanTypeDefinition) element));
			}
		}

		return children.toArray(new FanStructureViewElement[0]);
	}
}
