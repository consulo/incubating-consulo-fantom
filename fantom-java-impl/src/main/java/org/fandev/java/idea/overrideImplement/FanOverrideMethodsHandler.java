package org.fandev.java.idea.overrideImplement;

import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.language.Language;
import consulo.language.editor.generation.OverrideMethodHandler;
import consulo.language.psi.PsiFile;
import consulo.project.Project;
import jakarta.annotation.Nonnull;
import org.fandev.lang.fan.FanFileType;
import org.fandev.lang.fan.FanLanguage;

/**
 * Date: Sep 26, 2009
 * Time: 4:06:16 PM
 *
 * @author Dror Bereznitsky
 */
@ExtensionImpl
public class FanOverrideMethodsHandler implements OverrideMethodHandler
{
	public boolean isValidFor(final Editor editor, final PsiFile psiFile)
	{
		return psiFile != null && FanFileType.INSTANCE.equals(psiFile.getFileType());
	}

	public void invoke(final Project project, final Editor editor, final PsiFile file)
	{
	//	FanOverrideImplementUtil.invokeOverrideImplement(project, editor, file, false);
	}

	public boolean startInWriteAction()
	{
		return false;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return FanLanguage.INSTANCE;
	}
}
