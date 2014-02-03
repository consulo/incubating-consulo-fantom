package org.fandev.lang.fan;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.LanguageFileType;

/**
 *
 * @author Dror
 * @date Dec 12, 2008 12:00:55 AM
 */
public class FanSupportLoader extends FileTypeFactory {
	@Deprecated
    public static final LanguageFileType FAN = FanFileType.INSTANCE;
	@Deprecated
    public static final PodFileType POD = PodFileType.POD_FILE_TYPE;

    @Override
	public void createFileTypes(@NotNull final FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(FAN, FAN.getDefaultExtension());
        fileTypeConsumer.consume(POD, POD.getDefaultExtension());
    }

    public String getFileExtension() {
      return "fan";
    }
}
