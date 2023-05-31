package org.fandev.impl.lang.fan.psi.impl.statements.typedefs;

import consulo.language.ast.ASTNode;
import org.fandev.impl.lang.fan.FanElementTypes;
import org.fandev.impl.lang.fan.psi.api.statements.typeDefs.FanInheritanceClause;
import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.impl.lang.fan.psi.impl.FanBaseElementImpl;
import org.fandev.impl.lang.fan.psi.stubs.FanReferenceListStub;
import consulo.language.psi.StubBasedPsiElement;

/**
 * Date: Mar 18, 2009
 * Time: 10:52:49 PM
 *
 * @author Dror Bereznitsky
 */
public class FanInheritanceClauseImpl extends FanBaseElementImpl<FanReferenceListStub> implements FanInheritanceClause,
		StubBasedPsiElement<FanReferenceListStub>
{
	public FanInheritanceClauseImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	public FanInheritanceClauseImpl(final FanReferenceListStub fanReferenceListStub)
	{
		super(fanReferenceListStub, FanElementTypes.INHERITANCE_CLAUSE);
	}

	public String toString()
	{
		return "Inheritance clause";
	}

	public FanCodeReferenceElement[] getReferenceElements()
	{
		return findChildrenByClass(FanCodeReferenceElement.class);
	}
}
