package org.fandev.impl.lang.fan.structure.elements;

import consulo.codeEditor.Editor;
import consulo.fileEditor.structureView.StructureViewTreeElement;
import consulo.fileEditor.structureView.tree.Filter;
import consulo.fileEditor.structureView.tree.Grouper;
import consulo.fileEditor.structureView.tree.Sorter;
import consulo.language.editor.structureView.TextEditorBasedStructureViewModel;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import org.fandev.impl.lang.fan.structure.elements.impl.FanFileStructureViewElement;

import javax.annotation.Nonnull;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 4:25:13 PM
 */
public class FanStructureViewModel extends TextEditorBasedStructureViewModel
{
	private PsiElement myRoot;

	public FanStructureViewModel(@Nonnull final PsiFile psiFile)
	{
		super(psiFile);
		myRoot = psiFile;
	}

	public FanStructureViewModel(final PsiElement psiFile, final Editor editor)
	{
		super(editor);
		myRoot = psiFile;
	}

	protected PsiFile getPsiFile()
	{
		return myRoot.getContainingFile();
	}

	@Nonnull
	public StructureViewTreeElement getRoot()
	{
		return new FanFileStructureViewElement(myRoot);
	}

	@Nonnull
	public Grouper[] getGroupers()
	{
		return Grouper.EMPTY_ARRAY;
	}

	@Nonnull
	public Sorter[] getSorters()
	{
		return new Sorter[]{Sorter.ALPHA_SORTER};
	}

	@Nonnull
	public Filter[] getFilters()
	{
		return Filter.EMPTY_ARRAY;
	}
}
