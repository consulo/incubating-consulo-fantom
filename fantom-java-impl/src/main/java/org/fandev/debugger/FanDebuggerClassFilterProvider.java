package org.fandev.debugger;

import com.intellij.java.debugger.ui.classFilter.ClassFilter;
import com.intellij.java.debugger.ui.classFilter.DebuggerClassFilterProvider;
import consulo.annotation.component.ExtensionImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: Sep 3, 2009
 * Time: 12:17:52 AM
 *
 * @author Dror Bereznitsky
 */
@ExtensionImpl
public class FanDebuggerClassFilterProvider implements DebuggerClassFilterProvider
{
	@Override
	public List<ClassFilter> getFilters()
	{
		final ArrayList<ClassFilter> list = new ArrayList<ClassFilter>();
		return list;
	}
}
