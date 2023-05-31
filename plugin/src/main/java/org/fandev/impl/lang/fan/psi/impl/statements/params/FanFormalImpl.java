package org.fandev.impl.lang.fan.psi.impl.statements.params;

import javax.annotation.Nonnull;

import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.params.FanFormal;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
import org.fandev.impl.lang.fan.psi.impl.statements.FanVariableBaseImpl;
import consulo.language.psi.util.PsiTreeUtil;

/**
 * Date: Aug 20, 2009
 * Time: 12:43:30 PM
 *
 * @author Dror Bereznitsky
 */
public class FanFormalImpl extends FanVariableBaseImpl implements FanFormal
{
	public FanFormalImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Nonnull
	public PsiElement getDeclarationScope()
	{
		final FanFuncTypeElement owner = PsiTreeUtil.getParentOfType(this, FanFuncTypeElement.class);
		assert owner != null;
		return owner;
	}

	@Override
	public PsiElement getNameIdentifier()
	{
		return findChildByType(FanElementTypes.NAME_ELEMENT);
	}
}
