package org.fandev.java.runner;

import consulo.annotation.component.ExtensionImpl;
import consulo.execution.RunnerAndConfigurationSettings;
import consulo.execution.action.ConfigurationContext;
import consulo.execution.action.Location;
import consulo.execution.action.RuntimeConfigurationProducer;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import org.fandev.lang.fan.psi.FanFile;

/**
 * @author Dror Bereznitsky
 * @date Jan 20, 2009 9:10:00 PM
 */
@ExtensionImpl
public class FanScriptRunConfigurationProducer extends RuntimeConfigurationProducer implements Cloneable
{
	private PsiElement mySourceElement;

	public FanScriptRunConfigurationProducer()
	{
		super(FanScriptRunConfigurationType.getInstance());
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
			final FanFile fanFile = (FanFile) file;
			mySourceElement = element;
			return FanScriptRunConfigurationType.getInstance().createConfigurationByLocation(location);

		}

		return null;
	}

	public int compareTo(final Object o)
	{
		return PREFERED;
	}
}
