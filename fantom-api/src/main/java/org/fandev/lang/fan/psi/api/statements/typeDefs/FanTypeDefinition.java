package org.fandev.lang.fan.psi.api.statements.typeDefs;

import javax.annotation.Nonnull;

import consulo.language.psi.PsiNameIdentifierOwner;
import consulo.language.psi.PsiNamedElement;
import org.fandev.lang.fan.psi.FanClassType;
import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.api.statements.FanTopLevelDefintion;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMember;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
import org.fandev.lang.fan.psi.api.topLevel.FanTopStatement;

import javax.annotation.Nullable;

import consulo.language.psi.PsiElement;
import consulo.language.util.IncorrectOperationException;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 5:42:21 PM
 */
public interface FanTypeDefinition extends FanElement, PsiNamedElement, FanTopLevelDefintion,
		FanTopStatement, PsiNameIdentifierOwner, FanMember
{
	FanClassType[] getExtendsListTypes();

	FanTypeDefinition getSuperClass();

	FanTypeDefinition[] getSupers();

	String getQualifiedName();

	boolean isEnum();

	boolean isInterface();

	@Nonnull
	String getPodName();

	@Nonnull
	FanSlot[] getSlots();

	@Nonnull
	FanSlot[] getSlots(final String modifier);

	@Nonnull
	FanMethod[] getFanMethods();

	@Nonnull
	FanMethod[] getFanMethods(final String modifier);

	@Nonnull
	FanField[] getFanFields();

	@Nonnull
	FanField[] getFanFields(final String modifier);

	FanField getFieldByName(final String name);

	@Nullable
	FanMethod getMethodByName(@Nonnull final String name);

	FanTypeDefinition getSuperType();

	String getJavaQualifiedName();

	PsiElement getBodyElement();

	PsiElement addMemberDeclaration(@Nonnull final PsiElement decl, final PsiElement anchorBefore) throws IncorrectOperationException;
}
