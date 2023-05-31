package org.fandev.impl.lang.fan.structure;

import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.fileEditor.structureView.StructureViewBuilder;
import consulo.fileEditor.structureView.StructureViewModel;
import consulo.fileEditor.structureView.TreeBasedStructureViewBuilder;
import consulo.language.Language;
import consulo.language.editor.structureView.PsiStructureViewFactory;
import consulo.language.psi.PsiFile;
import org.fandev.lang.fan.FanLanguage;
import org.fandev.impl.lang.fan.structure.elements.FanStructureViewModel;

import javax.annotation.Nonnull;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 4:23:57 PM
 */
@ExtensionImpl
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

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return FanLanguage.INSTANCE;
	}
}
