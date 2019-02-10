package org.fandev.lang.fan.structure.elements.itemsPresentations;

import javax.annotation.Nullable;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import consulo.ide.IconDescriptorUpdaters;
import consulo.ui.image.Image;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 4:49:41 PM
 */
public abstract class FanItemPresentation implements ItemPresentation
{
	protected final PsiElement myElement;

	protected FanItemPresentation(final PsiElement myElement)
	{
		this.myElement = myElement;
	}

	@Override
	@Nullable
	public String getLocationString()
	{
		return null;
	}

	@Override
	@Nullable
	public Image getIcon()
	{
		return IconDescriptorUpdaters.getIcon(myElement, 0);
	}
}
