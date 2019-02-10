package org.fandev.lang.fan.psi.impl.statements.typedefs;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import org.fandev.icons.Icons;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanEnumValue;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;

/**
 * Date: Mar 28, 2009
 * Time: 3:38:11 PM
 *
 * @author Dror Bereznitsky
 */
public class FanEnumDefinitionImpl extends FanTypeDefinitionImpl implements FanEnumDefinition
{
	FanEnumValue[] fanEnumValues;

	public FanEnumDefinitionImpl(final FanTypeDefinitionStub stubElement)
	{
		super(stubElement, FanElementTypes.CLASS_DEFINITION);
	}

	public FanEnumDefinitionImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public String toString()
	{
		return "Enum definition";
	}

	@Override
	public PsiElement setName(@Nonnull @NonNls final String name) throws IncorrectOperationException
	{
		// TODO rename
		return this;
	}

	@Override
	public boolean isInterface()
	{
		return false;
	}

	public boolean isAnnotationType()
	{
		return false;
	}

	@Override
	public boolean isEnum()
	{
		return false;
	}

	@Override
	public void subtreeChanged()
	{
		this.fanSlots = null;
		this.fanFields = null;
		this.fanMethods = null;
		super.subtreeChanged();
	}


	@Override
	public FanEnumValue[] getEnumValues()
	{
		if(fanEnumValues == null)
		{
			final PsiElement element = findChildByType(FanElementTypes.ENUM_BODY);
			final List<FanEnumValue> list = new ArrayList<FanEnumValue>();
			if(element != null)
			{
				final PsiElement[] bodyEls = element.getChildren();
				for(final PsiElement bodyEl : bodyEls)
				{
					if(bodyEl instanceof FanEnumValue)
					{
						list.add((FanEnumValue) bodyEl);
					}
				}
			}
			fanEnumValues = list.toArray(new FanEnumValue[0]);
		}
		return fanEnumValues;
	}

	@Override
	public FanTypeDefinition getContainingClass()
	{
		return null;
	}

	@Override
	protected Icon getIconInner()
	{
		return Icons.ENUM;
	}

	@Override
	protected IElementType getBodyElementType()
	{
		return FanElementTypes.ENUM_BODY;
	}
}
