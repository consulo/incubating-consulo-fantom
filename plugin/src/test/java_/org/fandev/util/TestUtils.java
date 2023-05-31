package org.fandev.util;

import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiFileFactory;
import consulo.project.Project;
import consulo.language.file.FileTypeManager;
import consulo.language.util.IncorrectOperationException;
import consulo.util.lang.LocalTimeCounter;

/**
 *
 * @author Dror Bereznitsky
 * @date Jan 13, 2009 10:41:30 AM
 */
public class TestUtils {
    public static final String TEMP_FILE = "temp.fan";

    public static PsiFile createPseudoPhysicalFanFile(final Project project, final String text) throws IncorrectOperationException {
        return createPseudoFanFile(project, TEMP_FILE, text);
    }

    public static PsiFile createPseudoFanFile(final Project project, final String fileName, final String text) throws IncorrectOperationException
	{
        return PsiFileFactory.getInstance(project).createFileFromText(
                fileName,
                FileTypeManager.getInstance().getFileTypeByFileName(fileName),
                text,
                LocalTimeCounter.currentTime(),
                true);
    }
}
