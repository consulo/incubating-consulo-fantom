package org.fandev.lang.fan;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import consulo.fantom.FantomIcons;
import consulo.fileTypes.ArchiveFileType;
import consulo.ui.image.Image;

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

	@NotNull
	@Override
	public String getDefaultExtension()
	{
		return DEFAULT_EXTENSION;
	}

	@NotNull
	@Override
	public String getDescription()
	{
		return FanBundle.message("fan.filetype.description");
	}

	@NotNull
	@Override
	public String getId()
	{
		return "Pod";
	}
}
