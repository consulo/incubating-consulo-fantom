package org.fandev.lang.fan.psi.impl.statements.typedefs.members;

import javax.annotation.Nonnull;
import javax.swing.Icon;

import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.modifiers.FanModifierList;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.lang.fan.psi.impl.FanEnumReferenceType;
import org.fandev.lang.fan.psi.stubs.FanEnumValueStub;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.util.IncorrectOperationException;
import consulo.awt.TargetAWT;
import consulo.ide.IconDescriptorUpdaters;

/**
 * Date: Mar 31, 2009
 * Time: 11:24:38 PM
 *
 * @author Dror Bereznitsky
 */
public class FanEnumValueImpl extends FanBaseElementImpl<FanEnumValueStub> implements FanEnumValue
{
	public FanEnumValueImpl(final FanEnumValueStub fanEnumValueStub, @Nonnull final IStubElementType iStubElementType)
	{
		super(fanEnumValueStub, iStubElementType);
	}

	public FanEnumValueImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	@Nonnull
	public FanType getType()
	{
		return new FanEnumReferenceType((FanEnumDefinition) getContainingClass());
	}

	@Nullable
	@Override
	public FanType getDeclaredType()
	{
		return getType();
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
	@Nonnull
	public PsiElement getNameIdentifier()
	{
		return findChildByType(FanElementTypes.NAME_ELEMENT);
	}

	@Override
	public FanTypeDefinition getContainingClass()
	{
		// Parent is body, grand parent is class
		final PsiElement parent = getParent().getParent();
		if(parent instanceof FanEnumDefinition)
		{
			return (FanTypeDefinition) parent;
		}
		throw new IllegalStateException("Have an enum value " + getName() + " with no enum: " + this);
	}

	public boolean isDeprecated()
	{
		return false;
	}

	@Override
	@Nullable
	public FanModifierList getModifierList()
	{
		return null;
	}

	@Override
	public boolean hasModifierProperty(final String name)
	{
		// TODO
		return false;
	}

	@Override
	public boolean hasExplicitModifier(String name)
	{
		return false;
	}

	@Override
	public PsiElement setName(@Nonnull @NonNls final String name) throws IncorrectOperationException
	{
		//TODO implement method
		return this;
	}

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
			public Icon getIcon(final boolean open)
			{
				return TargetAWT.to(IconDescriptorUpdaters.getIcon(FanEnumValueImpl.this, Iconable.ICON_FLAG_VISIBILITY | Iconable.ICON_FLAG_READ_STATUS));
			}
		};
	}
}
