package org.fandev.sdk;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.fandev.lang.fan.FanBundle;
import org.fandev.utils.FanUtil;
import org.fandev.utils.OSUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.SdkType;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import consulo.fantom.FantomIcons;
import consulo.roots.types.BinariesOrderRootType;
import consulo.roots.types.DocumentationOrderRootType;
import consulo.roots.types.SourcesOrderRootType;
import consulo.ui.image.Image;

/**
 * @author Dror Bereznitsky
 * @date Jan 18, 2009 4:27:47 PM
 */
public class FanSdkType extends SdkType
{
	private static final Logger log = Logger.getInstance("org.fandev.sdk.FanSdkType");

	@NotNull
	public static FanSdkType getInstance()
	{
		return EP_NAME.findExtension(FanSdkType.class);
	}

	public static final String FAN_LAUNCHER_WIN = "fanlaunch.bat";
	public static final String FAN_LAUNCHER_UNIX = "fanlaunch";

	public static final String JSTUB_WIN = "jstub.exe";
	public static final String JSTUB_UNIX = "fanlaunch";
	public static final String FAN_LAUNCHER_DIR = "bin";
	public static final String FAN_LIB_DIR = "lib/fan";
	public static final String SRC_DIR = "src";
	public static final String FAN_SRC_DIR = "fan";

	public FanSdkType()
	{
		super("FAN_SDK");
	}

	@Override
	public boolean isRootTypeApplicable(OrderRootType type)
	{
		return type == SourcesOrderRootType.getInstance() || type == BinariesOrderRootType.getInstance() || type == DocumentationOrderRootType.getInstance();
	}

	@Override
	public Image getIcon()
	{
		return FantomIcons.Fantom;
	}

	@Override
	public String getVersionString(@NotNull final String sdkHome)
	{
		FanUtil.setFanHome(sdkHome);
		ClassLoader cl = FanUtil.getSysClassloader(sdkHome);
		try
		{
			final Class podClass = Class.forName("fan.sys.Pod", true, cl);
			final Class versionClass = Class.forName("fan.sys.Version", true, cl);
			final Object o = podClass.getDeclaredMethod("find", String.class, boolean.class).invoke(null, "sys", Boolean.TRUE);
			final Object versionObj = podClass.getDeclaredMethod("version").invoke(o);
			return (String) versionClass.getDeclaredMethod("toStr").invoke(versionObj);
		}
		catch(Exception e)
		{
			log.warn("Could not determine Fantom SDK version", e);
		}
		finally
		{
			cl = null;
		}
		return "";
	}

	@NotNull
	@Override
	public Collection<String> suggestHomePaths()
	{
		String s = suggestHomePath();
		if(s != null)
		{
			return Collections.singletonList(s);
		}
		return super.suggestHomePaths();
	}

	private String suggestHomePath()
	{
		final String path = findFanLauncher();
		if(path != null)
		{
			return path;
		}

		if(SystemInfo.isWindows)
		{
			return "C:/dev/fan";
		}
		else if(SystemInfo.isMac)
		{
			return "/applications/fan";
		}
		else if(SystemInfo.isUnix || SystemInfo.isLinux)
		{
			return "/usr/share/fantom";
		}

		return null;
	}

	@Override
	public boolean isValidSdkHome(final String path)
	{
		return (new File(path + File.separator + getFanLauncherPath())).exists();
	}

	@Override
	public String suggestSdkName(final String currentSdkName, final String sdkHome)
	{
		return "Fantom SDK " + getVersionString(sdkHome);
	}

	@NotNull
	@Override
	public String getPresentableName()
	{
		return FanBundle.message("fan.sdk.title");
	}

	@Override
	public void setupSdkPaths(final Sdk sdk)
	{
		if(sdk == null)
		{
			throw new IllegalArgumentException("Sdk should not be null");
		}

		final VirtualFile sysJar = sdk.getHomeDirectory().findFileByRelativePath("lib/java/sys.jar");
		if(sysJar != null)
		{
			VirtualFile sysSources = sdk.getHomeDirectory().findFileByRelativePath("src/sys/java");
			if(sysSources == null)
			{
				// For older versions of fan
				sysSources = sdk.getHomeDirectory().findFileByRelativePath("src/jfan");
			}
			final SdkModificator sdkModificator = sdk.getSdkModificator();

			sdkModificator.addRoot(sysJar, OrderRootType.CLASSES);
			if(sysSources == null)
			{
				sdkModificator.addRoot(sysSources, OrderRootType.SOURCES);
			}

			final VirtualFile extDir = getExtDir(sdk);
			// Add all jars in <FAN_HOME>/lib/java/ext/<OS> dir
			if(extDir.exists())
			{
				for(final VirtualFile lib : extDir.getChildren())
				{
					if("jar".equals(lib.getExtension()))
					{
						sdkModificator.addRoot(lib, OrderRootType.CLASSES);
					}
				}
			}

			sdkModificator.commitChanges();
		}
	}

	public static VirtualFile getExtDir(final Sdk sdk)
	{
		if(sdk == null)
		{
			throw new IllegalArgumentException("Sdk should not be null");
		}
		// This needs more work. E.g., 64-bit linux, win32 versions of swt.jar
		// But right now these are the packaged directory names in 1.0.51
		final String os = SystemInfo.isWindows ? "win32-x86" : SystemInfo.isMac ? "macosx-x86_64" : "linux-x86";
		//os += SystemInfo.is32Bit ? "-x86" : "-x86_64";
		return sdk.getHomeDirectory().findFileByRelativePath("lib/java/ext/" + os);
	}

	public static String getFanLauncher()
	{
		if(SystemInfo.isWindows)
		{
			return FAN_LAUNCHER_WIN;
		}
		else if(SystemInfo.isUnix)
		{
			return FAN_LAUNCHER_UNIX;
		}
		throw new IllegalStateException(FanBundle.message("os.not.supported"));
	}

	public static String getFanLauncherPath()
	{
		if(SystemInfo.isWindows)
		{
			return FAN_LAUNCHER_DIR + File.separator + FAN_LAUNCHER_WIN;
		}
		else if(SystemInfo.isUnix)
		{
			return FAN_LAUNCHER_DIR + File.separator + FAN_LAUNCHER_UNIX;
		}
		throw new IllegalStateException(FanBundle.message("os.not.supported"));
	}

	public static String getJStubPath()
	{
		if(SystemInfo.isWindows)
		{
			return FAN_LAUNCHER_DIR + File.separator + JSTUB_WIN;
		}
		else if(SystemInfo.isUnix)
		{
			return FAN_LAUNCHER_DIR + File.separator + JSTUB_UNIX;
		}
		throw new IllegalStateException(FanBundle.message("os.not.supported"));
	}

	public static String getFanLibDir()
	{
		return FAN_LIB_DIR;
	}

	public static String getSrcDir()
	{
		return SRC_DIR;
	}

	public static String getFanSrcDir()
	{
		return FAN_SRC_DIR;
	}

	@Nullable
	private static String findFanLauncher()
	{
		final String exePath = getFanLauncherPath();
		final String interpreterName = getFanLauncher();

		final String pathByName = OSUtil.findExecutableByName(interpreterName);
		if(pathByName == null)
		{
			return null;
		}
		return pathByName.endsWith(exePath) ? pathByName.substring(0, pathByName.length() - exePath.length()) : null;
	}
}
