package org.fandev.impl.lang.fan.psi.impl.statements.typedefs;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import org.fandev.impl.icons.Icons;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanBuildScriptDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanSlot;
import org.fandev.impl.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.fandev.utils.FanUtil;
import org.jetbrains.annotations.NonNls;
import consulo.language.ast.IElementType;
import consulo.language.util.IncorrectOperationException;
import consulo.ui.image.Image;

/**
 * Date: July 29, 2009
 *
 * @author Fred Simon
 */
public class FanBuildScriptDefinitionImpl extends FanTypeDefinitionImpl implements FanBuildScriptDefinition
{
	FanField[] fanFields;

	public FanBuildScriptDefinitionImpl(final FanTypeDefinitionStub stubElement)
	{
		super(stubElement, FanElementTypes.BUILDSCRIPT_DEFINITION);
	}

	public FanBuildScriptDefinitionImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	public String toString()
	{
		return "BuildScript definition";
	}

	public PsiElement setName(@NonNls final String name) throws IncorrectOperationException
	{
		// TODO rename
		return this;
	}

	public boolean isInterface()
	{
		return false;
	}

	public boolean isAnnotationType()
	{
		return false;
	}

	public boolean isEnum()
	{
		return false;
	}

	@Override
	public void subtreeChanged()
	{
		this.fanFields = null;
		super.subtreeChanged();
	}

	@Nonnull
	@Override
	public FanField[] getFanFields()
	{
		if(fanFields == null)
		{
			final List<FanField> list = new ArrayList<FanField>();
			final PsiElement element = findChildByType(getBodyElementType());
			if(element != null)
			{
				final PsiElement[] bodyEls = element.getChildren();
				for(final PsiElement bodyEl : bodyEls)
				{
					if(FanUtil.isFanField(bodyEl))
					{
						list.add((FanField) bodyEl);
					}
				}
			}
			fanFields = list.toArray(new FanField[0]);
		}
		return fanFields;
	}

	@Nonnull
	public FanMethod[] getFanMethods()
	{
		return FanMethod.EMPTY_ARRAY;
	}

	@Nonnull
	public FanSlot[] getSlots()
	{
		return getFanFields();
	}

	@Override
	protected Image getIconInner()
	{
		return Icons.POD;
	}

	protected IElementType getBodyElementType()
	{
		return FanElementTypes.BUILDSCRIPT_BODY;
	}

	@Override
	public boolean hasModifierProperty(final String name)
	{
		return false;
	}

	@Override
	public FanTypeDefinition getContainingClass()
	{
		return null;
	}
}
