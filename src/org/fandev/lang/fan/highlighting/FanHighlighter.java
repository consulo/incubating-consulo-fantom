package org.fandev.lang.fan.highlighting;

import static org.fandev.lang.fan.FanTokenTypes.FAN_KEYWORDS;
import static org.fandev.lang.fan.FanTokenTypes.FAN_SYS_TYPE;

import java.util.HashMap;
import java.util.Map;

import org.fandev.lang.fan.FanHighlightingLexer;
import org.fandev.lang.fan.FanTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

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
	@NotNull
	public Lexer getHighlightingLexer()
	{
		return new FanHighlightingLexer();
	}

	@Override
	@NotNull
	public TextAttributesKey[] getTokenHighlights(final IElementType tokenType)
	{
		return pack(keys.get(tokenType));
	}
}
