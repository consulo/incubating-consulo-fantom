package org.fandev.lang.fan;

import consulo.fantom.FantomIcons;
import consulo.fantom.api.localize.FantomApiLocalize;
import consulo.fileTypes.ArchiveFileType;
import consulo.localize.LocalizeValue;
import consulo.ui.image.Image;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;

/**
 * @author Dror Bereznitsky
 * @date Feb 28, 2009 6:09:05 PM
 */
public class PodFileType extends ArchiveFileType
{
	public static final PodFileType POD_FILE_TYPE = new PodFileType();
	@NonNls
	public static final String DEFAULT_EXTENSION = "pod";

	public PodFileType()
	{
		super();
	}

	@Nonnull
	@Override
	public String getProtocol()
	{
		return "zip";
	}

	@Override
	public Image getIcon()
	{
		return FantomIcons.Fantom;
	}

	@Nonnull
	@Override
	public String getDefaultExtension()
	{
		return DEFAULT_EXTENSION;
	}

	@Nonnull
	@Override
	public LocalizeValue getDescription()
	{
		return FantomApiLocalize.podDescription();
	}

	@Nonnull
	@Override
	public String getId()
	{
		return "Pod";
	}
}
