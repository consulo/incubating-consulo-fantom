package org.fandev.lang.fan.psi.impl.statements.params;

import javax.annotation.Nonnull;

import consulo.language.ast.ASTNode;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
import org.fandev.lang.fan.psi.api.statements.FanParameterOwner;
import org.fandev.lang.fan.psi.api.statements.params.FanParameter;
import org.fandev.lang.fan.psi.impl.statements.FanVariableBaseImpl;
import consulo.language.psi.PsiElement;
import consulo.language.psi.util.PsiTreeUtil;

/**
 * Date: Apr 29, 2009
 * Time: 11:08:53 PM
 *
 * @author Dror Bereznitsky
 */
public class FanParameterImpl extends FanVariableBaseImpl implements FanParameter
{
	public FanParameterImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	@Nonnull
	public PsiElement getDeclarationScope()
	{
		final FanParameterOwner owner = PsiTreeUtil.getParentOfType(this, FanParameterOwner.class);
		assert owner != null;
		return owner;
	}

	@Override
	public String toString()
	{
		return "Parameter";
	}

	@Override
	public PsiElement getNameIdentifier()
	{
		return findChildByType(FanElementTypes.NAME_ELEMENT);
	}

	@Override
	public FanDefaultValue getDefaultValue()
	{
		return findChildByClass(FanDefaultValue.class);
	}
}
