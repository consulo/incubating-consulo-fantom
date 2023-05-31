package org.fandev.actions.generation;

import consulo.language.psi.PsiDirectory;
import org.fandev.icons.Icons;
import org.fandev.lang.fan.FanBundle;

/**
 * Date: Apr 1, 2009
 * Time: 11:44:49 PM
 *
 * @author Dror Bereznitsky
 */
public class NewFanEnumAction extends NewFanActionBase
{
	protected NewFanEnumAction()
	{
		super(FanBundle.message("newenum.menu.action.text"), FanBundle.message("newenum.menu.action.description"), Icons.ENUM);
	}

	protected String getTemplateName()
	{
		return "FanEnum.fan";
	}

	protected String getDialogPrompt()
	{
		return FanBundle.message("newenum.dlg.prompt");
	}

	protected String getDialogTitle()
	{
		return FanBundle.message("newenum.dlg.title");
	}

	protected String getCommandName()
	{
		return FanBundle.message("newenum.command.name");
	}

	protected String getActionName(final PsiDirectory directory, final String newName)
	{
		return FanBundle.message("newenum.progress.text", newName);
	}
}
