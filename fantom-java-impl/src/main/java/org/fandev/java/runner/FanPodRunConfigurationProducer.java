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
 * Date: Sep 5, 2009
 * Time: 12:06:57 AM
 *
 * @author Dror Bereznitsky
 */
@ExtensionImpl
public class FanPodRunConfigurationProducer extends RuntimeConfigurationProducer implements Cloneable
{
	private PsiElement mySourceElement;

	public FanPodRunConfigurationProducer()
	{
		super(FanPodRunConfigurationType.getInstance());
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
			return FanPodRunConfigurationType.getInstance().createConfigurationByLocation(location);
		}

		return null;
	}

	public int compareTo(final Object o)
	{
		return PREFERED;
	}
}
