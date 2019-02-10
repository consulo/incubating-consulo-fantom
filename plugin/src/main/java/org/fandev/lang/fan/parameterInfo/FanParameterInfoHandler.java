package org.fandev.lang.fan.parameterInfo;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.psi.FanElement;
import org.fandev.lang.fan.psi.FanType;
import org.fandev.lang.fan.psi.api.FanResolveResult;
import org.fandev.lang.fan.psi.api.statements.FanDefaultValue;
import org.fandev.lang.fan.psi.api.statements.FanVariable;
import org.fandev.lang.fan.psi.api.statements.arguments.FanArgument;
import org.fandev.lang.fan.psi.api.statements.expressions.FanReferenceExpression;
import org.fandev.lang.fan.psi.api.statements.params.FanParameter;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanConstructor;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanMethod;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.CreateParameterInfoContext;
import com.intellij.lang.parameterInfo.ParameterInfoContext;
import com.intellij.lang.parameterInfo.ParameterInfoHandler;
import com.intellij.lang.parameterInfo.ParameterInfoUIContext;
import com.intellij.lang.parameterInfo.UpdateParameterInfoContext;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.util.PsiTreeUtil;

/**
 * Date: Sep 14, 2009
 * Time: 11:42:19 PM
 *
 * @author Dror Bereznitsky
 */
public class FanParameterInfoHandler implements ParameterInfoHandler<FanElement, FanResolveResult>
{
	public boolean couldShowInLookup()
	{
		return true;
	}

	public Object[] getParametersForLookup(final LookupElement item, final ParameterInfoContext context)
	{
		/*final List<? extends PsiElement> elements = JavaCompletionUtil.getAllPsiElements((LookupItem) item);

		if(elements != null)
		{
			final List<PsiMethod> methods = new ArrayList<PsiMethod>();
			for(final PsiElement element : elements)
			{
				if(element instanceof PsiMethod)
				{
					methods.add((PsiMethod) element);
				}
			}
			return methods.toArray(new Object[0]);
		}   */

		return null;
	}

	public Object[] getParametersForDocumentation(final FanResolveResult p, final ParameterInfoContext context)
	{
		return new Object[0];
	}

	public FanElement findElementForParameterInfo(final CreateParameterInfoContext context)
	{
		return findAnchorElement(context.getEditor().getCaretModel().getOffset(), context.getFile());
	}

	public void showParameterInfo(@Nonnull final FanElement place, final CreateParameterInfoContext context)
	{
		final PsiElement parent = place.getParent();
		FanResolveResult[] variants = FanResolveResult.EMPTY_ARRAY;
		if(parent instanceof FanReferenceExpression)
		{
			variants = ((FanReferenceExpression) parent).getSameNameVariants();
		}
		context.setItemsToShow(variants);
		context.showHint(place, place.getTextRange().getStartOffset(), this);
	}

	public FanElement findElementForUpdatingParameterInfo(final UpdateParameterInfoContext context)
	{
		return findAnchorElement(context.getEditor().getCaretModel().getOffset(), context.getFile());
	}

	public void updateParameterInfo(@Nonnull final FanElement o, final UpdateParameterInfoContext context)
	{

	}

	public String getParameterCloseChars()
	{
		return ",){}";
	}

	public boolean tracksParameterIndex()
	{
		return true;
	}

	public void updateUI(final FanResolveResult resolveResult, final ParameterInfoUIContext context)
	{
		final CodeInsightSettings settings = CodeInsightSettings.getInstance();

		final PsiNamedElement element = (PsiNamedElement) resolveResult.getElement();
		if(element == null || !element.isValid())
		{
			context.setUIComponentEnabled(false);
			return;
		}

		int highlightStartOffset = -1;
		int highlightEndOffset = -1;

		final StringBuffer buffer = new StringBuffer();

		if(element instanceof FanMethod)
		{
			final FanMethod method = (FanMethod) element;
			if(settings.SHOW_FULL_SIGNATURES_IN_PARAMETER_INFO)
			{
				if(!(method instanceof FanConstructor))
				{
					final FanType returnType = method.getReturnType();
					if(returnType != null)
					{
						buffer.append(returnType.getPresentableText());
						buffer.append(" ");
					}
				}
				buffer.append(element.getName());
				buffer.append("(");
			}

			final int currentParameter = context.getCurrentParameterIndex();

			final FanParameter[] parms = method.getParameterList().getParameters();
			int numParams = parms.length;
			if(numParams > 0)
			{
				for(int j = 0; j < numParams; j++)
				{
					final FanParameter parm = parms[j];

					final int startOffset = buffer.length();

					appendParameterText(parm, buffer);

					final int endOffset = buffer.length();

					if(j < numParams - 1)
					{
						buffer.append(", ");
					}

					if(context.isUIComponentEnabled() && j == currentParameter)
					{
						highlightStartOffset = startOffset;
						highlightEndOffset = endOffset;
					}
				}
			}
			else
			{
				buffer.append("no parameters");
			}

			if(settings.SHOW_FULL_SIGNATURES_IN_PARAMETER_INFO)
			{
				buffer.append(")");
			}

		}
		else if(element instanceof FanTypeDefinition)
		{
			buffer.append("no parameters");
		}
		else if(element instanceof FanVariable)
		{
			final FanType type = ((FanVariable) element).getType();
			//TODO [dror] hanlde closures here
		}

		final boolean isDeprecated = false;//resolveResult instanceof PsiDocCommentOwner && ((PsiDocCommentOwner) resolveResult).isDeprecated();

		context.setupUIComponentPresentation(buffer.toString(), highlightStartOffset, highlightEndOffset, !context.isUIComponentEnabled(),
				isDeprecated, false, context.getDefaultParameterColor());
	}

	private FanElement findAnchorElement(final int offset, final PsiFile file)
	{
		final PsiElement element = file.findElementAt(offset);
		if(element == null)
		{
			return null;
		}

		final FanArgument arg = PsiTreeUtil.getParentOfType(element, FanArgument.class);
		if(arg != null)
		{
			final FanElement argList = arg.getArgumentList();
			if(argList != null)
			{
				return argList;
			}
		}
		return null;
	}

	private void appendParameterText(final FanParameter parameter, final StringBuffer buffer)
	{
		final FanType t = parameter.getType();
		buffer.append(t.getPresentableText());
		final String name = parameter.getName();
		if(name != null)
		{
			buffer.append(" ");
			buffer.append(name);
		}
		final FanDefaultValue defaultValue = parameter.getDefaultValue();
		if(defaultValue != null)
		{
			buffer.append(" (");
			buffer.append(defaultValue.getText());
			buffer.append(")");
		}
	}
}
