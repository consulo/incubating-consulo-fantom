package org.fandev.lang.fan.highlighting;

import consulo.codeEditor.DefaultLanguageHighlighterColors;
import consulo.colorScheme.TextAttributesKey;

/**
 * @author VISTALL
 * @since 03.02.14
 */
public interface FanHighlighterKeys
{
	TextAttributesKey FAN_KEYWORD = TextAttributesKey.createTextAttributesKey("FAN_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

	TextAttributesKey FAN_TYPES = TextAttributesKey.createTextAttributesKey("FAN_TYPES", DefaultLanguageHighlighterColors.CLASS_NAME);

	TextAttributesKey FAN_STRING = TextAttributesKey.createTextAttributesKey("FAN_STRING", DefaultLanguageHighlighterColors.STRING);

	TextAttributesKey FAN_NUMBER = TextAttributesKey.createTextAttributesKey("FAN_NUMBER", DefaultLanguageHighlighterColors.NUMBER);

	TextAttributesKey FAN_PARENTHS = TextAttributesKey.createTextAttributesKey("FAN_PARENTHS", DefaultLanguageHighlighterColors.PARENTHESES);

	TextAttributesKey FAN_BRACKETS = TextAttributesKey.createTextAttributesKey("FAN_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);

	TextAttributesKey FAN_BRACES = TextAttributesKey.createTextAttributesKey("FAN_BRACES", DefaultLanguageHighlighterColors.BRACES);

	TextAttributesKey FAN_DOC_COMMENT = TextAttributesKey.createTextAttributesKey("FAN_DOC_COMMENT", DefaultLanguageHighlighterColors.DOC_COMMENT);

	TextAttributesKey FAN_LINE_COMMENT = TextAttributesKey.createTextAttributesKey("FAN_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
}
