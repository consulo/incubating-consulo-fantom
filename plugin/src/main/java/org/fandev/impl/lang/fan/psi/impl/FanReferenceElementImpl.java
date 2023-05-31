package org.fandev.impl.lang.fan.psi.impl;

import javax.annotation.Nonnull;

import consulo.document.util.TextRange;
import consulo.language.psi.PsiReference;
import org.fandev.impl.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.psi.FanReferenceElement;
import org.fandev.lang.fan.psi.FanType;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;
import consulo.language.psi.stub.IStubElementType;
import consulo.language.psi.stub.StubElement;
import consulo.language.util.IncorrectOperationException;

/**
 * @author Dror Bereznitsky
 * @date Feb 19, 2009 11:34:46 PM
 */
public abstract class FanReferenceElementImpl extends FanBaseElementImpl implements FanReferenceElement
{
	protected FanReferenceElementImpl(final StubElement stubElement, @Nonnull final IStubElementType iStubElementType)
	{
		super(stubElement, iStubElementType);
	}

	protected FanReferenceElementImpl(final ASTNode astNode)
	{
		super(astNode);
	}

	@Override
	public PsiReference getReference()
	{
		return this;
	}

	public String getReferenceName()
	{
		final PsiElement nameElement = getReferenceNameElement();
		if(nameElement != null)
		{
			return nameElement.getText();
		}
		return null;
	}

	public PsiElement getReferenceNameElement()
	{
		final PsiElement element = findChildByType(FanTokenTypes.IDENTIFIER_TOKENS_SET);
		if(element != null)
		{
			return element;
		}
		return null;
	}

	public PsiElement getElement()
	{
		return this;
	}

	public TextRange getRangeInElement()
	{
		final PsiElement refNameElement = getReferenceNameElement();
		if(refNameElement != null)
		{
			final int offsetInParent = refNameElement.getStartOffsetInParent();
			return new TextRange(offsetInParent, offsetInParent + refNameElement.getTextLength());
		}
		return new TextRange(0, getTextLength());
	}

	//TODO implement this method
	public PsiElement handleElementRename(final String s) throws IncorrectOperationException
	{
		return null;
	}

	public PsiElement bindToElement(@Nonnull final PsiElement element) throws IncorrectOperationException
	{
		return null;
	}

	@Nonnull
	public FanType[] getTypeArguments()
	{
		/*final FanTypeArgumentList typeArgsList = getTypeArgumentList();
        if (typeArgsList == null) return PsiType.EMPTY_ARRAY;

        final FanTypeElement[] args = typeArgsList.getTypeArgumentElements();
        if (args.length == 0) return PsiType.EMPTY_ARRAY;
        PsiType[] result = new PsiType[args.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = args[i].getType();
        }

        return result;*/

		return FanType.EMPTY_ARRAY;
	}
}
