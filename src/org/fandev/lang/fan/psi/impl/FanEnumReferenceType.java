package org.fandev.lang.fan.psi.impl;

import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanEnumDefinition;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.search.GlobalSearchScope;

/**
 * Date: Sep 8, 2009
 * Time: 8:51:12 AM
 *
 * @author Dror Bereznitsky
 */
public class FanEnumReferenceType implements FanType
{
	private final FanEnumDefinition myEnum;

	public FanEnumReferenceType(final FanEnumDefinition myEnum)
	{
		this.myEnum = myEnum;
	}

	public FanEnumDefinition resolve()
	{
		return myEnum;
	}

	public String getClassName()
	{
		return myEnum.getName();
	}

	@Override
	public String getPresentableText()
	{
		return myEnum.getName();
	}

	public String getCanonicalText()
	{
		return myEnum.getName();
	}

	public String getInternalCanonicalText()
	{
		return getCanonicalText();
	}

	public boolean isValid()
	{
		return myEnum.isValid();
	}

	public boolean equalsToText(@NonNls final String text)
	{
		return text.endsWith(getPresentableText()) && //optimization
				text.equals(getCanonicalText());
	}

	@NotNull
	public GlobalSearchScope getResolveScope()
	{
		return myEnum.getResolveScope();
	}
}
