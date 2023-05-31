package org.fandev.actions.generation;

import org.fandev.icons.Icons;
import org.fandev.lang.fan.FanBundle;
import consulo.language.psi.PsiDirectory;

/**
 * @author Dror Bereznitsky
 * @date Apr 1, 2009 3:41:23 PM
 */
public class NewFanClassAction extends NewFanActionBase
{
	protected NewFanClassAction()
	{
		super(FanBundle.message("newclass.menu.action.text"), FanBundle.message("newclass.menu.action.description"), Icons.CLASS);
	}

	protected String getTemplateName()
	{
		return "FanClass.fan";
	}

	protected String getDialogPrompt()
	{
		return FanBundle.message("newclass.dlg.prompt");
	}

	protected String getDialogTitle()
	{
		return FanBundle.message("newclass.dlg.title");
	}

	protected String getCommandName()
	{
		return FanBundle.message("newclass.command.name");
	}

	protected String getActionName(final PsiDirectory directory, final String newName)
	{
		return FanBundle.message("newclass.progress.text", newName);
	}

}
