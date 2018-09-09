package org.fandev.lang.fan.psi;

import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 2:49:39 PM
 */

public interface FanFile extends PsiFile
{
	String getPodName();

	FanTypeDefinition[] getTypeDefinitions();

	@Nullable
	FanTypeDefinition getTypeByName(@NotNull final String name);

	public FanTopLevelDefintion[] getTopLevelDefinitions();
}
