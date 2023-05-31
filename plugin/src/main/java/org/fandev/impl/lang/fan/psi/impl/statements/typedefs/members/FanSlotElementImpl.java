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
package org.fandev.impl.lang.fan.psi.impl.statements.typedefs.members;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import consulo.component.util.Iconable;
import consulo.language.psi.PsiNameIdentifierOwner;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.NamedStub;
import consulo.navigation.ItemPresentation;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.modifiers.FanFacet;
import org.fandev.lang.fan.psi.api.modifiers.FanModifierList;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMember;
import org.fandev.impl.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.impl.lang.fan.psi.impl.modifiers.FanModifierListImpl;
import org.jetbrains.annotations.NonNls;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import consulo.language.util.IncorrectOperationException;
import consulo.language.icon.IconDescriptorUpdaters;
import consulo.ui.image.Image;

/**
 * @author freds
 * @date Jan 24, 2009
 */
public abstract class FanSlotElementImpl<T extends NamedStub> extends FanBaseElementImpl<T> implements FanMember, PsiNameIdentifierOwner
{
	protected FanSlotElementImpl(final T t, @Nonnull final IStubElementType iStubElementType)
	{
		super(t, iStubElementType);
	}

	protected FanSlotElementImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public int getTextOffset()
	{
		final PsiElement identifier = getNameIdentifier();
		return identifier == null ? 0 : identifier.getTextRange().getStartOffset();
	}

	@Override
	public String getName()
	{
		final PsiElement psiId = getNameIdentifier();
		return psiId == null ? null : psiId.getText();
	}

	@Override
	@Nullable
	public PsiElement getNameIdentifier()
	{
		return findChildByType(FanElementTypes.NAME_ELEMENT);
	}

	@Override
	public PsiElement setName(@NonNls final String name) throws IncorrectOperationException
	{
		//TODO implement method
		return this;
	}

	@Override
	public FanTypeDefinition getContainingClass()
	{
		// Parent is body, grand parent is class
		final PsiElement parent = getParent().getParent();
		if(parent instanceof FanTypeDefinition)
		{
			return (FanTypeDefinition) parent;
		}
		throw new IllegalStateException("Have a slot " + getName() + " with no class: " + this);
	}

	@Override
	@Nullable
	public FanModifierList getModifierList()
	{
		final FanModifierListImpl list = findChildByClass(FanModifierListImpl.class);
		assert list != null;
		return list;
	}

	@Override
	public boolean hasModifierProperty(final String name)
	{
		final FanModifierList modifiers = getModifierList();
		if(modifiers != null)
		{
			return modifiers.hasModifierProperty(name);
		}
		return false;
	}

	@Override
	public boolean hasExplicitModifier(String name)
	{
		final FanModifierList modifiers = getModifierList();
		if(modifiers != null)
		{
			return modifiers.hasExplicitModifier(name);
		}
		return false;
	}

	public FanFacet[] getFacets()
	{
		return new FanFacet[0];
	}

	protected abstract Image getIconInner();

	@Override
	public ItemPresentation getPresentation()
	{
		return new ItemPresentation()
		{
			@Override
			public String getPresentableText()
			{
				return getName();
			}

			@Override
			@Nullable
			public String getLocationString()
			{
				final FanTypeDefinition clazz = getContainingClass();
				final String name = clazz.getQualifiedName();
				assert name != null;
				return "(in " + name + ")";
			}

			@Override
			@Nullable
			public Image getIcon()
			{
				return IconDescriptorUpdaters.getIcon(FanSlotElementImpl.this, Iconable.ICON_FLAG_VISIBILITY | Iconable.ICON_FLAG_READ_STATUS);
			}
		};
	}
}
