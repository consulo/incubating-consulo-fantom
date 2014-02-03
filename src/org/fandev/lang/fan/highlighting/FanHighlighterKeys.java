package org.fandev.lang.fan.highlighting;

import org.fandev.lang.fan.FanLanguage;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

/**
 * @author VISTALL
 * @since 03.02.14
 */
public interface FanHighlighterKeys
{
	TextAttributesKey FAN_KEYWORD = TextAttributesKey.createTextAttributesKey(FanLanguage.INSTANCE, DefaultLanguageHighlighterColors.KEYWORD);

	TextAttributesKey FAN_TYPES = TextAttributesKey.createTextAttributesKey(FanLanguage.INSTANCE, DefaultLanguageHighlighterColors.CLASS_NAME);

	TextAttributesKey FAN_STRING = TextAttributesKey.createTextAttributesKey(FanLanguage.INSTANCE, DefaultLanguageHighlighterColors.STRING);

	TextAttributesKey FAN_NUMBER = TextAttributesKey.createTextAttributesKey(FanLanguage.INSTANCE, DefaultLanguageHighlighterColors.NUMBER);

	TextAttributesKey FAN_PARENTHS = TextAttributesKey.createTextAttributesKey(FanLanguage.INSTANCE, DefaultLanguageHighlighterColors.PARENTHESES);

	TextAttributesKey FAN_BRACKETS = TextAttributesKey.createTextAttributesKey(FanLanguage.INSTANCE, DefaultLanguageHighlighterColors.BRACKETS);

	TextAttributesKey FAN_BRACES = TextAttributesKey.createTextAttributesKey(FanLanguage.INSTANCE, DefaultLanguageHighlighterColors.BRACES);

	TextAttributesKey FAN_DOC_COMMENT = TextAttributesKey.createTextAttributesKey(FanLanguage.INSTANCE,
			DefaultLanguageHighlighterColors.DOC_COMMENT);

	TextAttributesKey FAN_LINE_COMMENT = TextAttributesKey.createTextAttributesKey(FanLanguage.INSTANCE,
			DefaultLanguageHighlighterColors.LINE_COMMENT);
}
