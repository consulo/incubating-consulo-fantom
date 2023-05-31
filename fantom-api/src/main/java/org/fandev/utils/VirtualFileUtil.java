package org.fandev.utils;

import consulo.virtualFileSystem.LocalFileSystem;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.VirtualFileManager;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static consulo.util.io.FileUtil.toSystemIndependentName;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Roman Chernyatchik
 * @date: 05.03.2007
 */
public class VirtualFileUtil
{
	@NonNls
	public static final char VFS_PATH_SEPARATOR = '/';

	/**
	 * @param dir  directory
	 * @param name file name
	 * @return url for file with name in directory dir
	 */
	public static String constructUrl(@Nonnull final VirtualFile dir, final String name)
	{
		return dir.getUrl() + VFS_PATH_SEPARATOR + name;
	}

	/**
	 * Searches files for list of urls
	 *
	 * @param urls Urls
	 * @return found files or empty array
	 */
	@Nonnull
	public static VirtualFile[] getFiles(@Nonnull final List<String> urls)
	{
		final VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
		final List<VirtualFile> files = new ArrayList<VirtualFile>();
		for(final String url : urls)
		{
			if(TextUtil.isEmpty(url))
			{
				continue;
			}
			final VirtualFile file = virtualFileManager.findFileByUrl(url);
			if(file != null)
			{
				files.add(file);
			}
		}
		return files.toArray(new VirtualFile[0]);
	}

	/**
	 * @param url Url for virtual file
	 * @return file name with extension
	 */
	@Nonnull
	public static String getFileName(@Nonnull final String url)
	{
		final int index = url.lastIndexOf(VFS_PATH_SEPARATOR);
		return index < 0 ? url : url.substring(index + 1);
	}

	/**
	 * @param url Url for virtual file
	 * @return url for parent directory of virtual file
	 */
	@Nullable
	public static String getParentDir(@Nullable final String url)
	{
		if(url == null)
		{
			return null;
		}
		final int index = url.lastIndexOf(VFS_PATH_SEPARATOR);
		return index < 0 ? null : url.substring(0, index);
	}

	/**
	 * @param fileName Virtual file name
	 * @return file extension
	 */
	@Nullable
	public static String getExtension(@Nonnull final String fileName)
	{
		int index = fileName.lastIndexOf('.');
		return index < 0 ? null : fileName.substring(index + 1);
	}

	/**
	 * @param fileName virtual file name
	 * @return name without extension
	 */
	@Nonnull
	public static String removeExtension(@Nonnull final String fileName)
	{
		int i = fileName.length() - 1;
		for(; i >= 0; i--)
		{
			if(fileName.charAt(i) == '.')
			{
				return fileName.substring(0, i);
			}
		}
		return fileName;
	}

	/**
	 * Converts OS depended path to VirtualFile url
	 *
	 * @param path Path
	 * @return url
	 */
	@Nonnull
	public static String constructLocalUrl(@Nonnull final String path)
	{
		String myPath = toSystemIndependentName(path);
		if(!myPath.startsWith(String.valueOf(VFS_PATH_SEPARATOR)))
		{
			myPath = VFS_PATH_SEPARATOR + myPath;
		}
		return VirtualFileManager.constructUrl(LocalFileSystem.PROTOCOL, myPath);
	}

	@Nonnull
	public static String getNameWithoutExtension(@Nonnull final String fileName)
	{
		int index = fileName.lastIndexOf('.');
		return index < 0 ? fileName : fileName.substring(0, index);
	}

	/**
	 * For comparators. Compares files pathes.
	 *
	 * @param file1 file1
	 * @param file2 file2
	 * @return compare result
	 */
	public static int compareVirtualFiles(@Nonnull final VirtualFile file1, @Nonnull final VirtualFile file2)
	{
		final String path1 = file1.getPath();
		final String path2 = file2.getPath();
		return path1.compareToIgnoreCase(path2);
	}

	public static boolean isValid(@Nullable final VirtualFile file)
	{
		return file != null && file.isValid();
	}

	public static String buildUrl(@Nonnull final String rootUrl, @Nonnull final String relativePath)
	{
		return rootUrl +
				//#TODO normalize
				(rootUrl.endsWith(String.valueOf(VFS_PATH_SEPARATOR)) ? "" : VFS_PATH_SEPARATOR) + toSystemIndependentName(relativePath);
	}

	public static String buildSystemIndependentPath(@Nonnull final String rootPath, @Nonnull final String relativePath)
	{
		final String rootPathIN = toSystemIndependentName(rootPath);
		return rootPathIN + (rootPathIN.endsWith(String.valueOf(VFS_PATH_SEPARATOR)) ? "" : VFS_PATH_SEPARATOR) + toSystemIndependentName(relativePath);
	}

	/**
	 * Converst OS depended path to VirtualFile url.
	 *
	 * @param path Path
	 * @return If path is null or empty string returns null, otherwise  url
	 */
	@Nullable
	public static String pathToURL(@Nullable final String path)
	{
		if(TextUtil.isEmpty(path))
		{
			return null;
		}
		assert path != null;
		return constructLocalUrl(path);
	}

	public static VirtualFile findFileByLocalPath(@Nonnull final String generateScriptPath)
	{
		return VirtualFileManager.getInstance().findFileByUrl(constructLocalUrl(generateScriptPath));
	}

	public static VirtualFile refreshAndFindFileByLocalPath(@Nonnull final String generateScriptPath)
	{
		return VirtualFileManager.getInstance().refreshAndFindFileByUrl(constructLocalUrl(generateScriptPath));
	}

	public static boolean fileExists(@Nullable final VirtualFile file)
	{
		return file != null && file.exists();
	}

	@Nullable
	public static String getRelativePath(@Nonnull final String filePathOrUrl, @Nonnull final String rootPathOrUrl)
	{
		if(filePathOrUrl.length() < rootPathOrUrl.length())
		{
			return null;
		}
		String path = filePathOrUrl.substring(rootPathOrUrl.length());
		if(path.length() > 0 && path.charAt(0) == VFS_PATH_SEPARATOR)
		{
			path = path.substring(1);
		}
		return path;
	}

	public static class VirtualFilesComparator implements Comparator<VirtualFile>
	{
		public int compare(final VirtualFile file1, final VirtualFile file2)
		{
			return compareVirtualFiles(file1, file2);
		}
	}

	@Nonnull
	public static String convertToVFSPathAndNormalizeSlashes(@Nonnull final String path)
	{
		final String newPath = toSystemIndependentName(path);
		if(newPath.length() != 0 && newPath.charAt(newPath.length() - 1) == VFS_PATH_SEPARATOR)
		{
			return newPath.substring(0, newPath.length() - 1);
		}
		return newPath;
	}
}
