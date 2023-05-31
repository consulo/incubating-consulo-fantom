package org.fandev.impl.lang.fan.psi.impl.statements.typedefs.members;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import consulo.language.ast.ASTNode;
import consulo.navigation.ItemPresentation;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.modifiers.FanModifierList;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
import org.fandev.impl.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.impl.lang.fan.psi.impl.FanEnumReferenceType;
import org.fandev.impl.lang.fan.psi.stubs.FanEnumValueStub;
import org.jetbrains.annotations.NonNls;
import consulo.component.util.Iconable;
import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.util.IncorrectOperationException;
import consulo.language.icon.IconDescriptorUpdaters;
import consulo.ui.image.Image;

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
			public Image getIcon()
			{
				return IconDescriptorUpdaters.getIcon(FanEnumValueImpl.this, Iconable.ICON_FLAG_VISIBILITY | Iconable.ICON_FLAG_READ_STATUS);
			}
		};
	}
}
