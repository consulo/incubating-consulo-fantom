package org.fandev.lang.fan;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.file.LanguageFileType;
import consulo.virtualFileSystem.fileType.FileTypeConsumer;
import consulo.virtualFileSystem.fileType.FileTypeFactory;

import javax.annotation.Nonnull;

/**
 * @author Dror
 * @date Dec 12, 2008 12:00:55 AM
 */
@ExtensionImpl
public class FanSupportLoader extends FileTypeFactory
{
	@Deprecated
	public static final LanguageFileType FAN = FanFileType.INSTANCE;
	@Deprecated
	public static final PodFileType POD = PodFileType.POD_FILE_TYPE;

	@Override
	public void createFileTypes(@Nonnull final FileTypeConsumer fileTypeConsumer)
	{
		fileTypeConsumer.consume(FanFileType.INSTANCE);
		fileTypeConsumer.consume(POD, POD.getDefaultExtension());
	}
}
