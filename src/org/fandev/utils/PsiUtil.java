package org.fandev.utils;

import org.fandev.lang.fan.psi.api.modifiers.FanModifierListOwner;
import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMember;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * @author Dror Bereznitsky
 * @date Feb 21, 2009 3:30:29 PM
 */
public class PsiUtil
{
	public static boolean isAccessible(final PsiElement place, final FanMember member)
	{
		return true; //TODO [VISTALL]
	}

	public static int getFlags(final FanModifierListOwner paramPsiModifierListOwner, final boolean paramBoolean)
	{
		final PsiFile localPsiFile = paramPsiModifierListOwner.getContainingFile();

		final VirtualFile localVirtualFile = (localPsiFile == null) ? null : localPsiFile.getVirtualFile();

		final int enumFlag = ((paramPsiModifierListOwner instanceof FanTypeDefinition) && (((FanTypeDefinition) paramPsiModifierListOwner).isEnum())) ? 1 : 0;

		int mainFlag = (((paramPsiModifierListOwner.hasModifierProperty("final")) && (enumFlag == 0)) ? 1024 : 0) |
				(((paramPsiModifierListOwner.hasModifierProperty("static")) && (enumFlag == 0)) ? 512 : 0) |
				((paramBoolean) ? 2048 : 0) |
				((isExcluded(localVirtualFile, paramPsiModifierListOwner.getProject())) ? 4096 : 0);

		if(paramPsiModifierListOwner instanceof FanTypeDefinition)
		{
			if((paramPsiModifierListOwner.hasModifierProperty("abstract")) && (!(((FanTypeDefinition) paramPsiModifierListOwner).isInterface())))
			{
				mainFlag |= 256;
			}
		}
		return mainFlag;
	}

	public static boolean isExcluded(final VirtualFile paramVirtualFile, final Project paramProject)
	{
		return ((paramVirtualFile != null) && (ProjectRootManager.getInstance(paramProject).getFileIndex().isInSource(paramVirtualFile)) &&
				(CompilerManager.getInstance(paramProject).isExcludedFromCompilation(paramVirtualFile)));
	}

	@Nullable
	public static FanTopLevelDefintion findPreviousTopLevelElementByThisElement(final PsiElement element)
	{
		PsiElement parent = element.getParent();

		while(parent != null && !(parent instanceof FanTopLevelDefintion))
		{
			parent = parent.getParent();
		}

		if(parent == null)
		{
			return null;
		}
		return ((FanTopLevelDefintion) parent);
	}
}
