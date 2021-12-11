package org.fandev.lang.fan;

import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.fantom.FantomIcons;
import consulo.fantom.api.localize.FantomApiLocalize;
import consulo.localize.LocalizeValue;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;

/**
 * @author Dror
 * @date Dec 12, 2008 12:01:51 AM
 */
public class FanFileType extends LanguageFileType
{
	public static final FanFileType INSTANCE = new FanFileType();
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
	public LocalizeValue getDescription()
	{
		return FantomApiLocalize.fanFiletypeDescription();
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
