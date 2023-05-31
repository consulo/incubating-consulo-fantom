package org.fandev.lang.fan.structure.elements;

import consulo.navigation.Navigatable;
import consulo.language.psi.PsiElement;
import consulo.fileEditor.structureView.StructureViewTreeElement;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 4:32:51 PM
 */

public abstract class FanStructureViewElement implements StructureViewTreeElement
{
	final protected PsiElement myElement;

	protected FanStructureViewElement(final PsiElement myElement)
	{
		this.myElement = myElement;
	}

	public Object getValue()
	{
		return myElement.isValid() ? myElement : null;
	}

	public void navigate(final boolean b)
	{
		((Navigatable) myElement).navigate(b);
	}

	public boolean canNavigate()
	{
		return ((Navigatable) myElement).canNavigate();
	}

	public boolean canNavigateToSource()
	{
		return ((Navigatable) myElement).canNavigateToSource();
	}
}
