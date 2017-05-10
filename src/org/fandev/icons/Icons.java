package org.fandev.icons;

import javax.swing.Icon;

import com.intellij.openapi.util.IconLoader;
import consulo.annotations.DeprecationInfo;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 3:56:47 PM
 */
@Deprecated
@DeprecationInfo("See consulo.fantom.FantomIcons")
public interface Icons
{
	Icon POD = IconLoader.getIcon("/icons/structure/pod.png");
	Icon CLASS = IconLoader.getIcon("/icons/structure/class.png");
	Icon ABSTRACT_CLASS = IconLoader.getIcon("/icons/structure/abstract-class.png");
	Icon MIXIN = IconLoader.getIcon("/icons/structure/mixin.png");
	Icon ENUM = IconLoader.getIcon("/icons/structure/enum.png");
	Icon METHOD = IconLoader.getIcon("/icons/structure/method.png");
	Icon FIELD = IconLoader.getIcon("/icons/structure/field.png");
}
