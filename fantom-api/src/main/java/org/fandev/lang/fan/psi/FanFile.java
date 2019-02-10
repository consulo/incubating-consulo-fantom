package org.fandev.lang.fan.psi;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

import javax.annotation.Nullable;

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
	FanTypeDefinition getTypeByName(@Nonnull final String name);

	public FanTopLevelDefintion[] getTopLevelDefinitions();
}
