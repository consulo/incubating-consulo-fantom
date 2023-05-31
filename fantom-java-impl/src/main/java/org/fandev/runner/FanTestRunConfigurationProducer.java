package org.fandev.runner;

import consulo.annotation.component.ExtensionImpl;
import consulo.execution.RunnerAndConfigurationSettings;
import consulo.execution.action.ConfigurationContext;
import consulo.execution.action.Location;
import consulo.execution.action.RuntimeConfigurationProducer;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import org.fandev.lang.fan.psi.FanFile;

/**
 * Date: Sep 16, 2009
 * Time: 11:29:22 PM
 *
 * @author Dror Bereznitsky
 */
@ExtensionImpl
public class FanTestRunConfigurationProducer extends RuntimeConfigurationProducer implements Cloneable
{
	private PsiElement mySourceElement;

	public FanTestRunConfigurationProducer()
	{
		super(FanTestRunConfigurationType.getInstance());
	}

	public PsiElement getSourceElement()
	{
		return mySourceElement;
	}

	protected RunnerAndConfigurationSettings createConfigurationByElement(final Location location, final ConfigurationContext context)
	{
		final PsiElement element = location.getPsiElement();

		final PsiFile file = element.getContainingFile();
		if(file instanceof FanFile)
		{
			mySourceElement = element;
			return FanTestRunConfigurationType.getInstance().createConfigurationByLocation(location);
		}

		return null;
	}

	public int compareTo(final Object o)
	{
		return PREFERED;
	}
}
