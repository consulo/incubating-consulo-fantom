package org.fandev.lang.fan;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NonNls;
import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.fantom.FantomIcons;
import consulo.ui.image.Image;

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
	@Nonnull
	public String getId()
	{
		return "Fantom";
	}

	@Override
	@Nonnull
	public String getDescription()
	{
		return FanBundle.message("fan.filetype.description");
	}

	@Override
	@Nonnull
	public String getDefaultExtension()
	{
		return DEFAULT_EXTENSION;
	}

	@Override
	public Image getIcon()
	{
		return FantomIcons.Fantom;
	}
}
