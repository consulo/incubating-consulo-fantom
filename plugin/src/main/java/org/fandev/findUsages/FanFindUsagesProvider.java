package org.fandev.findUsages;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.statements.expressions.FanReferenceExpression;
import org.fandev.lang.fan.psi.api.statements.params.FanParameter;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanField;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;

import javax.annotation.Nullable;

import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;

/**
 * Date: Sep 18, 2009
 * Time: 12:22:02 AM
 *
 * @author Dror Bereznitsky
 */
public class FanFindUsagesProvider implements FindUsagesProvider
{

	public FanFindUsagesProvider()
	{
	}

	@Override
	@Nullable
	public FanWordsScanner getWordsScanner()
	{
		return new FanWordsScanner();
	}

	@Override
	public boolean canFindUsagesFor(@Nonnull final PsiElement psiElement)
	{
		return psiElement instanceof FanTypeDefinition ||
				psiElement instanceof FanMethod ||
				psiElement instanceof FanVariable;
	}

	@Override
	@Nullable
	public String getHelpId(@Nonnull final PsiElement psiElement)
	{
		return null;
	}

	@Override
	@Nonnull
	public String getType(@Nonnull final PsiElement element)
	{
		if(element instanceof FanTypeDefinition)
		{
			return "class";
		}
		if(element instanceof FanMethod)
		{
			return "method";
		}
		if(element instanceof FanField)
		{
			return "field";
		}
		if(element instanceof FanParameter)
		{
			return "parameter";
		}
		if(element instanceof FanVariable || element instanceof FanReferenceExpression)
		{
			return "variable";
		}
		return "";
	}

	@Override
	@Nonnull
	public String getDescriptiveName(@Nonnull final PsiElement element)
	{
		if(element instanceof FanTypeDefinition)
		{
			final FanTypeDefinition aClass = (FanTypeDefinition) element;
			final String qName = aClass.getQualifiedName();
			return qName == null ? "" : qName;
		}
		else if(element instanceof FanMethod)
		{
			final FanMethod method = (FanMethod) element;
			String result = method.getName();
			final FanTypeDefinition clazz = method.getContainingClass();
			if(clazz != null)
			{
				result += " of " + getDescriptiveName(clazz);
			}

			return result;
		}
		else if(element instanceof FanVariable)
		{
			final String name = ((FanVariable) element).getName();
			if(name != null)
			{
				return name;
			}
		}

		return "";
	}

	@Override
	@Nonnull
	public String getNodeText(@Nonnull final PsiElement element, final boolean useFullName)
	{
		if(element instanceof FanTypeDefinition)
		{
			String name = ((FanTypeDefinition) element).getQualifiedName();
			if(name == null || !useFullName)
			{
				name = ((FanTypeDefinition) element).getName();
			}
			if(name != null)
			{
				return name;
			}
		}
		else if(element instanceof FanMethod)
		{
			return ((FanMethod) element).getName();
		}
		else if(element instanceof FanVariable)
		{
			final String name = ((FanVariable) element).getName();
			if(name != null)
			{
				return name;
			}
		}
		return "";
	}
}

