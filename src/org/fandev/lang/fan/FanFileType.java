package org.fandev.lang.fan;

import javax.swing.Icon;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.fantom.FantomIcons;

/**
 * @author Dror
 * @date Dec 12, 2008 12:01:51 AM
 */
public class FanFileType extends LanguageFileType
{
	public static final FanFileType INSTANCE = new FanFileType();
	@NonNls
	public static final String DEFAULT_EXTENSION = "fan";

	private FanFileType()
	{
		super(FanLanguage.INSTANCE);
	}

	@Override
	@NotNull
	public String getId()
	{
		return "Fantom";
	}

	@Override
	@NotNull
	public String getDescription()
	{
		return FanBundle.message("fan.filetype.description");
	}

	@Override
	@NotNull
	public String getDefaultExtension()
	{
		return DEFAULT_EXTENSION;
	}

	@Override
	public Icon getIcon()
	{
		return FantomIcons.Fantom;
	}
}
