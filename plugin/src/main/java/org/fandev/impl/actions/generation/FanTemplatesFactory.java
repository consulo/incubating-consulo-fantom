package org.fandev.impl.actions.generation;

import consulo.annotation.component.ExtensionImpl;
import consulo.fantom.FantomIcons;
import consulo.fileTemplate.*;
import consulo.language.file.FileTypeManager;
import consulo.language.psi.PsiDirectory;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.language.util.IncorrectOperationException;
import consulo.logging.Logger;
import consulo.virtualFileSystem.VirtualFile;
import org.fandev.impl.PodModel;
import org.fandev.lang.fan.FanBundle;
import org.fandev.utils.VirtualFileUtil;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dror Bereznitsky
 * @date Apr 1, 2009 3:52:01 PM
 */
@ExtensionImpl
public class FanTemplatesFactory implements FileTemplateGroupDescriptorFactory
{
	@NonNls
	public static final String[] TEMPLATES = {
			"FanClass.fan",
			"FanMixin.fan",
			"FanEnum.fan",
			"FanBuildScript.fan"
	};
	private ArrayList<String> myCustomTemplates = new ArrayList<String>();
	private static final Logger logger = Logger.getInstance("org.fandev.actions.generation.FanTemplatesFactory");

	private static FanTemplatesFactory myInstance = new FanTemplatesFactory();

	public static FanTemplatesFactory getInstance()
	{
		return myInstance;
	}

	public void registerCustomTemplates(final String... templates)
	{
		for(final String template : templates)
		{
			myCustomTemplates.add(template);
		}
	}

	public FileTemplateGroupDescriptor getFileTemplatesDescriptor()
	{
		final FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor(FanBundle.message("file.template.group.title.fan"), FantomIcons.Fantom);
		final FileTypeManager fileTypeManager = FileTypeManager.getInstance();
		for(final String template : TEMPLATES)
		{
			group.addTemplate(new FileTemplateDescriptor(template, fileTypeManager.getFileTypeByFileName(template).getIcon()));
		}
		// register custom templates
		for(final String template : getInstance().getCustomTemplates())
		{
			group.addTemplate(new FileTemplateDescriptor(template, fileTypeManager.getFileTypeByFileName(template).getIcon()));
		}
		return group;
	}


	public static PsiFile createFromTemplate(final PsiDirectory directory, final String fileName, final String templateName,
											 @NonNls final EnumMap<TemplateProperty, String> parameters) throws IncorrectOperationException
	{
		final FileTemplate template = FileTemplateManager.getInstance().getDefaultTemplate(templateName);

		final Properties properties = new Properties(FileTemplateManager.getInstance().getDefaultProperties());
		for(final TemplateProperty propertyName : parameters.keySet())
		{
			properties.setProperty(propertyName.toString(), parameters.get(propertyName));
		}
		String text;
		try
		{
			text = template.getText(properties);
		}
		catch(Exception e)
		{
			throw new RuntimeException("Unable to load template for " + FileTemplateManager.getInstance().internalTemplateToSubject(templateName), e);
		}

		// Get rid of parameters with null settings from being written to the build.fan file.
		if(PodModel.BUILD_FAN.equals(fileName))
		{
			Pattern p = Pattern.compile("^.*\\[\\]$", Pattern.MULTILINE);
			Matcher m = p.matcher(text);
			text = m.replaceAll("");
			p = Pattern.compile("^.*= $", Pattern.MULTILINE);
			m = p.matcher(text);
			text = m.replaceAll("");
			p = Pattern.compile("^.*= \"\"$", Pattern.MULTILINE);
			m = p.matcher(text);
			text = m.replaceAll("");
			p = Pattern.compile("^.*= Version.fromStr\\(\"\"\\)$", Pattern.MULTILINE);
			m = p.matcher(text);
			text = m.replaceAll("");
		}
		final VirtualFile v = VirtualFileUtil.refreshAndFindFileByLocalPath(VirtualFileUtil.buildUrl(directory.getVirtualFile().getPath(), fileName));
		try
		{
			v.setBinaryContent(text.getBytes());
			final PsiManager manager = PsiManager.getInstance(directory.getProject());
			final PsiFile file = manager.findFile(v);
			return file;
		}
		catch(IOException e)
		{
			logger.error("IOException writing to build.fan!");
		}
		return null;
	}

	public String[] getCustomTemplates()
	{
		return myCustomTemplates.toArray(new String[0]);
	}
}
