package org.fandev.lang.fan.psi.impl.statements.params;

import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.psi.api.statements.params.FanFormal;
import org.fandev.lang.fan.psi.api.types.FanFuncTypeElement;
import org.fandev.lang.fan.psi.impl.statements.FanVariableBaseImpl;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;

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

	@NotNull
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
