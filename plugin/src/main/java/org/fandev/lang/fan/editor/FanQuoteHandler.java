package org.fandev.lang.fan.editor;

import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.Editor;
import consulo.codeEditor.HighlighterIterator;
import consulo.language.Language;
import consulo.language.ast.IElementType;
import consulo.language.editor.action.LanguageQuoteHandler;
import jakarta.annotation.Nonnull;
import org.fandev.lang.fan.FanLanguage;
import org.fandev.lang.fan.FanTokenTypes;

/**
 * Created by IntelliJ IDEA.
 * User: Dror
 * Date: Mar 13, 2009
 * Time: 9:45:40 AM
 */
@ExtensionImpl
public class FanQuoteHandler implements LanguageQuoteHandler
{
	public boolean isClosingQuote(final HighlighterIterator iterator, final int offset)
	{
		final IElementType tokenType = (IElementType) iterator.getTokenType();

		if(tokenType == FanTokenTypes.STRING_LITERAL)
		{
			final int start = iterator.getStart();
			final int end = iterator.getEnd();
			return end - start >= 1 && offset == end - 1;
		}
		return false;
	}

	public boolean isOpeningQuote(final HighlighterIterator iterator, final int offset)
	{
		final IElementType tokenType = (IElementType) iterator.getTokenType();

		//TODO use a more fine grained token type
		if(tokenType == FanTokenTypes.BAD_CHARACTER)
		{
			final int start = iterator.getStart();
			return offset == start;
		}
		return false;
	}

	public boolean hasNonClosedLiteral(final Editor editor, final HighlighterIterator iterator, final int offset)
	{
		return true;
	}

	public boolean isInsideLiteral(final HighlighterIterator iterator)
	{
		final IElementType tokenType = (IElementType) iterator.getTokenType();
		return tokenType == FanTokenTypes.STRING_LITERAL;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return FanLanguage.INSTANCE;
	}
}
