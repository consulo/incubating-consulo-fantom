package org.fandev.unscramble;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;

/**
 * Date: Sep 23, 2009
 * Time: 11:20:50 PM
 *
 * @author Dror Bereznitsky
 */
public class FanUnscrambleAction extends AnAction
{
	public void actionPerformed(final AnActionEvent e)
	{
		final Project localProject = e.getProject();

		final FanUnscrambleDialog localUnscrambleDialog = new FanUnscrambleDialog(localProject);

		localUnscrambleDialog.show();
	}

	public void update(final AnActionEvent e)
	{
		final Presentation localPresentation = e.getPresentation();

		final Project localProject = e.getProject();
		localPresentation.setEnabled(localProject != null);
	}
}
