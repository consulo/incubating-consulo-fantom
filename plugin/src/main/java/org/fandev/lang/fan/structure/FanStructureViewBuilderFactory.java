package org.fandev.lang.fan.structure;

import org.fandev.lang.fan.structure.elements.FanStructureViewModel;
import javax.annotation.Nonnull;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 4:23:57 PM
 */
public class FanStructureViewBuilderFactory implements PsiStructureViewFactory
{
	@Override
	public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile)
	{
		return new TreeBasedStructureViewBuilder()
		{
			@Override
			@Nonnull
			public StructureViewModel createStructureViewModel(Editor editor)
			{
				return new FanStructureViewModel(psiFile);
			}
		};
	}
}
