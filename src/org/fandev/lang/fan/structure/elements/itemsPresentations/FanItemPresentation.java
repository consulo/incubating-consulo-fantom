package org.fandev.lang.fan.structure.elements.itemsPresentations;

import javax.swing.Icon;

import org.jetbrains.annotations.Nullable;
import com.intellij.ide.IconDescriptorUpdaters;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;

/**
 *
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 4:49:41 PM
 */
public abstract class FanItemPresentation implements ItemPresentation {
    protected final PsiElement myElement;

    protected FanItemPresentation(final PsiElement myElement) {
        this.myElement = myElement;
    }

    @Override
	@Nullable
    public String getLocationString() {
        return null;
    }

    @Override
	@Nullable
    public Icon getIcon(final boolean open) {
        return IconDescriptorUpdaters.getIcon(myElement, 0);
    }

    @Nullable
    public TextAttributesKey getTextAttributesKey() {
        return null;
    }
}
