package org.fandev.utils;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.statements.blocks.FanPsiCodeBlock;
import org.fandev.lang.fan.psi.api.statements.expressions.FanClosureExpression;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanBuildScriptDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.impl.FanListReferenceType;
import org.fandev.lang.fan.psi.impl.FanMapType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import consulo.fantom.module.extension.FanModuleExtension;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;

/**
 * @author Dror Bereznitsky
 * @date Jan 21, 2009 4:48:30 PM
 */
public class FanUtil
{
	private static final Logger logger = Logger.getInstance("org.fandev.utils.FanUtil");

	static
	{
		System.setProperty("fan.debug", "true");
	}

	public static boolean isFanModuleType(@Nullable final Module module)
	{
		return module != null && ModuleUtilCore.getExtension(module, FanModuleExtension.class) != null;
	}

	@Deprecated
	public static Sdk getFanSdk(final Module module)
	{
		return ModuleUtilCore.getSdk(module, FanModuleExtension.class);
	}

	public static void setFanHome(final Module module)
	{
		setFanHome(getFanSdk(module));
	}

	public static void setFanHome(@NotNull final Sdk moduleSdk)
	{
		System.setProperty("fan.home", moduleSdk.getHomePath());
	}

	public static void setFanHome(@NotNull final String home)
	{
		System.setProperty("fan.home", home);
	}

	@Nullable
	public static URLClassLoader getSysClassloader(@NotNull final String sdkHome)
	{
		final VirtualFile sysJar = VirtualFileUtil.refreshAndFindFileByLocalPath(sdkHome + "/lib/java/sys.jar");
		try
		{
			if(sysJar.exists())
			{
				return new URLClassLoader(new URL[]{new java.io.File(sysJar.getPath()).toURI().toURL()});
			}
		}
		catch(Exception e)
		{
			logger.error("Could load sys.jar", e);
		}
		return null;
	}


	@Nullable
	public static FanTypeDefinition getContainingType(@NotNull final PsiElement element)
	{
		final PsiElement maybeClazz = PsiTreeUtil.getParentOfType(element, FanTypeDefinition.class, FanFile.class);
		if(FanUtil.isFanType(maybeClazz))
		{
			return (FanTypeDefinition) maybeClazz;
		}
		return null;
	}

	public static boolean isFanTypeDefinition(final PsiElement element)
	{
		return isOfType(element, FanTypeDefinition.class);
	}

	public static boolean isFanEnumDefinition(final PsiElement element)
	{
		return isOfType(element, FanEnumDefinition.class);
	}

	public static boolean isFanClosure(final PsiElement element)
	{
		return isOfType(element, FanClosureExpression.class);
	}

	public static boolean isPsiCodeBlock(final PsiElement element)
	{
		return isOfType(element, FanPsiCodeBlock.class);
	}

	public static boolean isFanMethod(final PsiElement element)
	{
		return isOfType(element, FanMethod.class);
	}

	public static boolean isFanField(final PsiElement element)
	{
		return isOfType(element, FanField.class);
	}

	public static boolean isFanVariable(final PsiElement element)
	{
		return isOfType(element, FanVariable.class);
	}

	public static boolean isFanFile(final PsiElement element)
	{
		return isOfType(element, FanFile.class);
	}

	public static boolean isFanType(final PsiElement element)
	{
		return isOfType(element, FanTypeDefinition.class);
	}

	public static boolean isFanBuildScript(final PsiElement element)
	{
		return isOfType(element, FanBuildScriptDefinition.class);
	}

	public static boolean isFanMapType(final FanType element)
	{
		return isOfType(element, FanMapType.class);
	}

	public static boolean isFanListType(final FanType element)
	{
		return isOfType(element, FanListReferenceType.class);
	}

	public static boolean isOfType(final PsiElement element, final Class<?> type)
	{
		return element != null && type.isAssignableFrom(element.getClass());
	}

	public static boolean isOfType(final FanType element, final Class<?> type)
	{
		return element != null && type.isAssignableFrom(element.getClass());
	}

	// helpers for converting FanModel parameter settings to and from strings to sets.
	public static String listToString(final List<String> set)
	{
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for(final String s : set)
		{
			if(i != 0)
			{
				sb.append(", ");
			}
			sb.append("\"");
			sb.append(s);
			sb.append("\"");
			i++;
		}
		return sb.toString();
	}

	public static String listPairToUrl(final List<Pair<String, String>> list)
	{
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for(final Pair<String, String> s : list)
		{
			if(i != 0)
			{
				sb.append(", ");
			}
			sb.append("`");
			sb.append(s.getFirst());
			if(!s.getFirst().endsWith("/"))
			{
				sb.append("/");
			}
			sb.append("`");
			i++;
		}
		return sb.toString();
	}

	public static String listPairToString(final List<Pair<String, String>> set)
	{
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for(final Pair<String, String> s : set)
		{
			if(i != 0)
			{
				sb.append(", ");
			}
			sb.append("\"");
			sb.append(s.getFirst());
			sb.append("\"");
			sb.append(" : ");
			sb.append("\"");
			sb.append(s.getSecond());
			sb.append("\"");
			i++;
		}
		return sb.toString();
	}

	public static String listPairToFirstString(final List<Pair<String, String>> set)
	{
		final StringBuilder sb = new StringBuilder();
		int i = 0;
		for(final Pair<String, String> s : set)
		{
			if(i != 0)
			{
				sb.append(", ");
			}
			sb.append(s.getFirst());
			i++;
		}
		return sb.toString();
	}

	public static List<String> stringToList(String value)
	{
		final List<String> list = new LinkedList<String>();
		if(TextUtil.isEmpty(value))
		{
			return list;
		}
		value = value.replaceAll("\"", "");
		final String[] values = value.split(",");
		for(int i = 0; i < values.length; i++)
		{
			list.add(values[i].trim());
		}
		return list;
	}

	public static List<Pair<String, String>> urlToListPair(String value)
	{
		final List<Pair<String, String>> list = new LinkedList<Pair<String, String>>();
		if(TextUtil.isEmpty(value))
		{
			return list;
		}
		value = value.replaceAll("`", "");
		final String[] values = value.split(",");
		for(int i = 0; i < values.length; i++)
		{
			final String s = values[i].trim();
			list.add(new Pair<String, String>(s, s));
		}
		return list;
	}

	public static List<Pair<String, String>> stringToListPair(String value)
	{
		final List<Pair<String, String>> list = new LinkedList<Pair<String, String>>();
		if(TextUtil.isEmpty(value))
		{
			return list;
		}
		value = value.replaceAll("\"", "");
		final String[] values = value.split(",");
		for(int i = 0; i < values.length; i++)
		{
			final String[] element = values[i].split(":{1}?");
			if(element.length > 1)
			{
				String t = element[1];
				if(element.length > 2)
				{
					final StringBuilder sb = new StringBuilder();
					for(int j = 1; j < element.length; j++)
					{
						if(TextUtil.isEmpty(element[j]))
						{
							sb.append("::");
						}
						else
						{
							sb.append(element[j]);
						}
					}
					t = sb.toString();
				}
				final Pair<String, String> pair = new Pair<String, String>(element[0].trim(), t.trim());
				list.add(pair);
			}
		}
		return list;
	}

	public static List<Pair<String, String>> firstStringToListPair(String value)
	{
		final List<Pair<String, String>> list = new LinkedList<Pair<String, String>>();
		if(TextUtil.isEmpty(value))
		{
			return list;
		}
		value = value.replaceAll("\"", "");
		final String[] values = value.split(",");
		for(int i = 0; i < values.length; i++)
		{
			final String s = values[i].trim();
			final Pair<String, String> pair = new Pair<String, String>(s, s);
			list.add(pair);
		}
		return list;
	}
}
