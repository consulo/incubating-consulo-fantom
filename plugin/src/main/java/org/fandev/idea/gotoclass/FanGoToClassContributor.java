/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fandev.idea.gotoclass;

import java.util.Collection;
import java.util.Set;

import org.fandev.index.FanIndex;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.stubs.index.FanShortClassNameIndex;
import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.StubIndex;

/**
 * @author freds
 * @date Jan 27, 2009
 */
public class FanGoToClassContributor implements ChooseByNameContributor
{
	@Override
	public String[] getNames(final Project project, final boolean includeNonProjectItems)
	{
		final Collection<String> classNames = StubIndex.getInstance().getAllKeys(FanShortClassNameIndex.KEY, project);
		if(includeNonProjectItems)
		{
			final FanIndex fanIndex = project.getComponent(FanIndex.class);
			classNames.addAll(fanIndex.getAllTypeNames());
		}
		return classNames.toArray(new String[0]);
	}

	@Override
	@SuppressWarnings({"SuspiciousToArrayCall"})
	public NavigationItem[] getItemsByName(final String name, final String pattern, final Project project, final boolean includeNonProjectItems)
	{
		final GlobalSearchScope scope = includeNonProjectItems ? null : GlobalSearchScope.projectScope(project);
		final Collection<FanTypeDefinition> classes = StubIndex.getInstance().get(FanShortClassNameIndex.KEY, name, project, scope);
		if(includeNonProjectItems)
		{
			final FanIndex fanIndex = project.getComponent(FanIndex.class);
			final Set<PsiFile> psiFileSet = fanIndex.getAllPsiFiles();
			for(final PsiFile psiFile : psiFileSet)
			{
				final FanTypeDefinition[] psiFileClasses = ((FanFile) psiFile).getTypeDefinitions();
				for(final FanTypeDefinition aClass : psiFileClasses)
				{
					if(aClass.getName().equals(name))
					{
						classes.add(aClass);
					}
				}
			}
		}
		return classes.toArray(new NavigationItem[0]);
	}
}
