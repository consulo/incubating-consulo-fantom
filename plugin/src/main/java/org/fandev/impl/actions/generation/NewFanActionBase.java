package org.fandev.impl.actions.generation;

import consulo.application.CommonBundle;
import consulo.ui.ex.awt.Messages;
import consulo.language.psi.PsiDirectory;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.util.IncorrectOperationException;
import consulo.ide.action.CreateElementActionBase;
import consulo.project.Project;
import consulo.ui.image.Image;
import org.fandev.lang.fan.FanFileType;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.function.Consumer;

/**
 * @author Dror Bereznitsky
 * @date Apr 1, 2009 3:37:03 PM
 */
public abstract class NewFanActionBase extends CreateElementActionBase
{
	protected NewFanActionBase(final String text, final String description, final Image icon)
	{
		super(text, description, icon);
	}

	protected void invokeDialog(final Project project, final PsiDirectory directory, Consumer<PsiElement[]> elementsConsumer)
	{
		final MyInputValidator validator = new MyInputValidator(project, directory);
		Messages.showInputDialog(project, getDialogPrompt(), getDialogTitle(), Messages.getQuestionIcon(), "", validator);

		elementsConsumer.accept(validator.getCreatedElements());
	}

	protected String getErrorTitle()
	{
		return CommonBundle.getErrorTitle();
	}

	@Nonnull
	protected PsiElement[] create(final String newName, final PsiDirectory directory) throws Exception
	{
		final EnumMap<TemplateProperty, String> parameters = new EnumMap<TemplateProperty, String>(TemplateProperty.class);
		parameters.put(TemplateProperty.NAME, newName);
		parameters.put(TemplateProperty.NAME_LOWER_CASE, newName.toLowerCase());
		addTemplateParams(parameters);
		final PsiFile file = createClassFromTemplate(directory, newName, getTemplateName(), parameters);
		return new PsiElement[]{file};
	}

	protected void addTemplateParams(final EnumMap<TemplateProperty, String> parameters)
	{
	}

	protected abstract String getTemplateName();

	protected PsiFile createClassFromTemplate(final PsiDirectory directory, final String className, String templateName,
																   @NonNls final EnumMap<TemplateProperty, String> parameters) throws IncorrectOperationException
	{
		return FanTemplatesFactory.createFromTemplate(directory, className + "." + FanFileType.DEFAULT_EXTENSION, templateName, parameters);
	}

	protected abstract String getDialogPrompt();

	protected abstract String getDialogTitle();
}
