package org.fandev.lang.fan.structure.elements.itemsPresentations;

import javax.annotation.Nullable;
import javax.swing.Icon;

import consulo.awt.TargetAWT;
import consulo.ide.IconDescriptorUpdaters;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;

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
	public Icon getIcon(final boolean open)
	{
		return TargetAWT.to(IconDescriptorUpdaters.getIcon(myElement, 0));
	}
}
