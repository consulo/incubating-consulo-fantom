package org.fandev.lang.fan;

import javax.annotation.Nonnull;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.LanguageFileType;

/**
 * @author Dror
 * @date Dec 12, 2008 12:00:55 AM
 */
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

	public String getFileExtension()
	{
		return "fan";
	}
}
