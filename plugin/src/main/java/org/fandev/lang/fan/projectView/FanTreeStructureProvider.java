package org.fandev.lang.fan.projectView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.annotation.Nullable;

import org.fandev.index.FanIndex;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.SelectableTreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.BasePsiNode;
import com.intellij.ide.projectView.impl.nodes.NamedLibraryElement;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * Date: Mar 13, 2009
 * Time: 4:09:00 PM
 *
 * @author Dror Bereznitsky
 */
public class FanTreeStructureProvider implements SelectableTreeStructureProvider
{
	private Project myProject;
	private FanIndex fanIndex;

	public FanTreeStructureProvider(final Project project, FanIndex fanIndex)
	{
		myProject = project;
		this.fanIndex = fanIndex;
	}

	public Collection<AbstractTreeNode> modify(final AbstractTreeNode parent, final Collection<AbstractTreeNode> children, final ViewSettings settings)
	{
		final Collection<AbstractTreeNode> result = new ArrayList<AbstractTreeNode>();
		// Add pod types to library tree view
		if(parent != null)
		{
			Object o = parent.getValue();
			if(o instanceof NamedLibraryElement)
			{
				addLibraryTypes(settings, result, o);
			}
		}

		result.addAll(children);
		return result;
	}

	private void addLibraryTypes(final ViewSettings settings, final Collection<AbstractTreeNode> result, final Object o)
	{
		final NamedLibraryElement libraryElement = (NamedLibraryElement) o;
		final Set<PsiFile> types = fanIndex.getLibraryPsiFiles(libraryElement.getName());
		for(final PsiFile typeVirtualFile : types)
		{
			final FanFile psiFile = (FanFile) typeVirtualFile;
			final FanTypeDefinition[] classes = psiFile.getTypeDefinitions();
			for(final FanTypeDefinition aClass : classes)
			{
				result.add(new FanTypeTreeNode(myProject, aClass, settings));
			}
		}
	}

	public Object getData(final Collection<AbstractTreeNode> selected, final String dataName)
	{
		return null;
	}

	public PsiElement getTopLevelElement(final PsiElement element)
	{
		return null;
	}

	private class FanTypeTreeNode extends BasePsiNode<FanTypeDefinition>
	{
		private FanTypeTreeNode(final Project project, final FanTypeDefinition psiClass, final ViewSettings viewSettings)
		{
			super(project, psiClass, viewSettings);
		}

		@Nullable
		@Override
		protected Collection<AbstractTreeNode> getChildrenImpl()
		{
			return Collections.emptyList();
		}

		@Override
		protected void updateImpl(PresentationData presentationData)
		{
			presentationData.setPresentableText(getValue().getName());
		}
	}
}
