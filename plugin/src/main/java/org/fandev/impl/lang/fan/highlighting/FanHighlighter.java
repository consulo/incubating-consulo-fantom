package org.fandev.impl.lang.fan.highlighting;

import consulo.colorScheme.TextAttributesKey;
import consulo.language.ast.IElementType;
import consulo.language.ast.TokenSet;
import consulo.language.editor.highlight.SyntaxHighlighterBase;
import consulo.language.lexer.Lexer;
import org.fandev.impl.lang.fan.FanHighlightingLexer;
import org.fandev.impl.lang.fan.FanTokenTypes;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static org.fandev.impl.lang.fan.FanTokenTypes.FAN_KEYWORDS;
import static org.fandev.impl.lang.fan.FanTokenTypes.FAN_SYS_TYPE;

/**
 * @author Dror Bereznitsky
 * @date Dec 22, 2008 10:58:32 PM
 */
public class FanHighlighter extends SyntaxHighlighterBase implements FanHighlighterKeys
{
	private static final Map<IElementType, TextAttributesKey> keys = new HashMap<IElementType, TextAttributesKey>();

	static
	{
		safeMap(keys, FAN_KEYWORDS, FAN_KEYWORD);
		safeMap(keys, FanTokenTypes.STRING_LITERALS, FAN_STRING);
		safeMap(keys, TokenSet.create(FAN_SYS_TYPE), FAN_TYPES);
		safeMap(keys, FanTokenTypes.NUMERIC_LITERALS, FAN_NUMBER);

		keys.put(FanTokenTypes.FANDOC_LINE_COMMENT, FAN_DOC_COMMENT);
		keys.put(FanTokenTypes.END_OF_LINE_COMMENT, FAN_LINE_COMMENT);
		keys.put(FanTokenTypes.C_STYLE_COMMENT, FAN_LINE_COMMENT);
	}

	@Override
	@Nonnull
	public Lexer getHighlightingLexer()
	{
		return new FanHighlightingLexer();
	}

	@Override
	@Nonnull
	public TextAttributesKey[] getTokenHighlights(final IElementType tokenType)
	{
		return pack(keys.get(tokenType));
	}
}
