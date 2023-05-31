package org.fandev.lang.fan.psi;

import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ServiceAPI;
import consulo.annotation.component.ServiceImpl;
import consulo.ide.ServiceManager;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiFileFactory;
import consulo.project.Project;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.fandev.lang.fan.FanFileType;
import org.fandev.lang.fan.psi.api.statements.blocks.FanPsiCodeBlock;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.topLevel.FanTopStatement;

import javax.annotation.Nullable;

/**
 * Date: Sep 26, 2009
 * Time: 4:33:50 PM
 *
 * @author Dror Bereznitsky
 */
@ServiceAPI(ComponentScope.PROJECT)
@ServiceImpl
@Singleton
public class FanPsiElementFactory
{
	private Project myProject;

	private static String DUMMY = "dummy.";

	@Inject
	public FanPsiElementFactory(final Project project)
	{
		myProject = project;
	}

	public static FanPsiElementFactory getInstance(final Project project)
	{
		return ServiceManager.getService(project, FanPsiElementFactory.class);
	}

	@Nullable
	public FanTopStatement createTopElementFromText(final String text)
	{
		final PsiFile dummyFile = PsiFileFactory.getInstance(myProject).createFileFromText(DUMMY + FanFileType.INSTANCE.getDefaultExtension(), text);
		final PsiElement firstChild = dummyFile.getFirstChild();
		if(!(firstChild instanceof FanTopStatement))
		{
			return null;
		}

		return (FanTopStatement) firstChild;
	}

	public FanPsiCodeBlock createMethodBodyFromText(final String text)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("class foo {\n");
		sb.append("public Void bar() {\n");
		sb.append(text);
		sb.append("}");
		final FanFile file = createDummyFile(sb.toString());
		final FanTypeDefinition type = (FanTypeDefinition) file.getTopLevelDefinitions()[0];
		final FanMethod method = type.getFanMethods()[0];
		return method.getBody();
	}

	private FanFile createDummyFile(final String s, final boolean isPhisical)
	{
		return (FanFile) PsiFileFactory.getInstance(myProject).createFileFromText("DUMMY__." + FanFileType.INSTANCE.getDefaultExtension(),
				FanFileType.INSTANCE, s, System.currentTimeMillis(), isPhisical);
	}

	private FanFile createDummyFile(String s)
	{
		return createDummyFile(s, false);
	}
}
