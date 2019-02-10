package org.fandev.icons;

import com.intellij.openapi.util.IconLoader;
import consulo.annotations.DeprecationInfo;
import consulo.ui.image.Image;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 3:56:47 PM
 */
@Deprecated
@DeprecationInfo("See consulo.fantom.FantomIcons")
public interface Icons
{
	Image POD = IconLoader.getIcon("/icons/structure/pod.png");
	Image CLASS = IconLoader.getIcon("/icons/structure/class.png");
	Image ABSTRACT_CLASS = IconLoader.getIcon("/icons/structure/abstract-class.png");
	Image MIXIN = IconLoader.getIcon("/icons/structure/mixin.png");
	Image ENUM = IconLoader.getIcon("/icons/structure/enum.png");
	Image METHOD = IconLoader.getIcon("/icons/structure/method.png");
	Image FIELD = IconLoader.getIcon("/icons/structure/field.png");
}
