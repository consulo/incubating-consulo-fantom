package org.fandev.lang.fan.psi.impl.modifiers;

import consulo.language.ast.ASTNode;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.psi.api.modifiers.FanModifier;
import org.fandev.lang.fan.psi.api.modifiers.FanModifierList;
import org.fandev.lang.fan.psi.impl.FanBaseElementImpl;

/**
 * @author Dror Bereznitsky
 * @date Apr 2, 2009 2:42:18 PM
 */
public class FanModifierListImpl extends FanBaseElementImpl implements FanModifierList
{
	public FanModifierListImpl(ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public boolean hasModifierProperty(final String modifier)
	{
		// If no protection keyword is specified, the class defaults to public
		// If no protection keyword is specified, the slot defaults to public
		if(modifier.equals(FanModifier.PUBLIC))
		{
			return findChildByType(FanTokenTypes.PRIVATE_KEYWORD) == null &&
					findChildByType(FanTokenTypes.PROTECTED_KEYWORD) == null &&
					findChildByType(FanTokenTypes.INTERNAL_KEYWORD) == null;
		}

		return hasExplicitModifier(modifier);
	}

	//TODO check if we can extends this beyond the modifier set we have in PsiModifier
	@Override
	public boolean hasExplicitModifier(final String name)
	{
		if(name.equals(FanModifier.PUBLIC))
		{
			return findChildByType(FanTokenTypes.PUBLIC_KEYWORD) != null;
		}
		if(name.equals(FanModifier.ABSTRACT))
		{
			return findChildByType(FanTokenTypes.ABSTRACT_KEYWORD) != null;
		}
		if(name.equals(FanModifier.NATIVE))
		{
			return findChildByType(FanTokenTypes.NATIVE_KEYWORD) != null;
		}
		return hasOtherModifiers(name);
	}

	private boolean hasOtherModifiers(final String name)
	{
		if(name.equals(FanModifier.PRIVATE))
		{
			return findChildByType(FanTokenTypes.PRIVATE_KEYWORD) != null;
		}
		if(name.equals(FanModifier.PROTECTED))
		{
			return findChildByType(FanTokenTypes.PROTECTED_KEYWORD) != null;
		}
		if(name.equals(FanModifier.PACKAGE_LOCAL))
		{
			return findChildByType(FanTokenTypes.INTERNAL_KEYWORD) != null;
		}
		if(name.equals(FanModifier.STATIC))
		{
			return findChildByType(FanTokenTypes.STATIC_KEYWORD) != null;
		}
		if(name.equals(FanModifier.FINAL))
		{
			return findChildByType(FanTokenTypes.FINAL_KEYWORD) != null;
		}
		return name.equals(FanModifier.VOLATILE) && findChildByType(FanTokenTypes.VOLATILE_KEYWORD) != null;
	}
}
