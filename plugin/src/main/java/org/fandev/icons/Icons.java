package org.fandev.icons;

import consulo.annotation.DeprecationInfo;
import consulo.fantom.api.icon.FantomApiIconGroup;
import consulo.ui.image.Image;

/**
 * @author Dror Bereznitsky
 * @date Jan 10, 2009 3:56:47 PM
 */
@Deprecated
@DeprecationInfo("See consulo.fantom.FantomIcons")
public interface Icons
{
	Image POD = FantomApiIconGroup.structurePod();
	Image CLASS = FantomApiIconGroup.structureClass();
	Image ABSTRACT_CLASS = FantomApiIconGroup.structureAbstract_class();
	Image MIXIN = FantomApiIconGroup.structureMixin();
	Image ENUM = FantomApiIconGroup.structureEnum();
	Image METHOD = FantomApiIconGroup.structureMethod();
	Image FIELD = FantomApiIconGroup.structureField();
}
