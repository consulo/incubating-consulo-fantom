package org.fandev.lang.fan.psi.impl.statements.typedefs;

import javax.swing.Icon;

import org.fandev.icons.Icons;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanMixinDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.stubs.FanTypeDefinitionStub;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;

/**
 * Date: Mar 23, 2009
 * Time: 11:04:42 PM
 *
 * @author Dror Bereznitsky
 */
public class FanMixinDefinitionImpl extends FanTypeDefinitionImpl implements FanMixinDefinition
{
	public FanMixinDefinitionImpl(final FanTypeDefinitionStub stubElement)
	{
		super(stubElement, FanElementTypes.CLASS_DEFINITION);
	}

	public FanMixinDefinitionImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public String toString()
	{
		return "Mixin definition";
	}

	@Override
	public PsiElement setName(@NotNull @NonNls final String name) throws IncorrectOperationException
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
	public FanTypeDefinition getContainingClass()
	{
		return null;
	}

	@NotNull
	@Override
	protected Icon getIconInner()
	{
		return Icons.MIXIN;
	}

	@Override
	protected IElementType getBodyElementType()
	{
		return FanElementTypes.MIXIN_BODY;
	}
}
