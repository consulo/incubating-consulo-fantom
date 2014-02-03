package org.fandev.debugger;

import java.util.ArrayList;
import java.util.List;

import com.intellij.ui.classFilter.ClassFilter;
import com.intellij.ui.classFilter.DebuggerClassFilterProvider;

/**
 * Date: Sep 3, 2009
 * Time: 12:17:52 AM
 *
 * @author Dror Bereznitsky
 */
public class FanDebuggerClassFilterProvider implements DebuggerClassFilterProvider
{
	@Override
	public List<ClassFilter> getFilters()
	{
		final ArrayList<ClassFilter> list = new ArrayList<ClassFilter>();
		return list;
	}
}
