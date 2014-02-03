package org.fandev.lang.fan.psi.api.statements.typeDefs;

import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
import org.fandev.lang.fan.psi.api.topLevel.FanTopStatement;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.util.IncorrectOperationException;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 5:42:21 PM
 */
public interface FanTypeDefinition extends StubBasedPsiElement<FanTypeDefinitionStub>, PsiNamedElement, FanTopLevelDefintion,
		FanTopStatement
{
	@NotNull
	String getPodName();

	@NotNull
	FanSlot[] getSlots();

	@NotNull
	FanSlot[] getSlots(final String modifier);

	@NotNull
	FanMethod[] getFanMethods();

	@NotNull
	FanMethod[] getFanMethods(final String modifier);

	@NotNull
	FanField[] getFanFields();

	@NotNull
	FanField[] getFanFields(final String modifier);

	FanField getFieldByName(final String name);

	@Nullable
	FanMethod getMethodByName(@NotNull final String name);

	FanTypeDefinition getSuperType();

	String getJavaQualifiedName();

	PsiElement getBodyElement();

	PsiElement addMemberDeclaration(@NotNull final PsiElement decl, final PsiElement anchorBefore) throws IncorrectOperationException;
}
