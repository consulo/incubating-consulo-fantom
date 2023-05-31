package org.fandev.lang.fan.highlighting;

import consulo.annotation.component.ExtensionImpl;
import consulo.colorScheme.TextAttributesKey;
import consulo.colorScheme.setting.AttributesDescriptor;
import consulo.language.editor.colorScheme.setting.ColorSettingsPage;
import consulo.language.editor.highlight.SyntaxHighlighter;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dror Bereznitsky
 * @date Dec 22, 2008 10:09:15 PM
 */
@ExtensionImpl
public class FanColorsAndFontsPage implements ColorSettingsPage
{
	private static final AttributesDescriptor[] ATTRS = {};

	private static final Map<String, TextAttributesKey> ADDITIONAL_HIGHLIGHT_DESCRIPTORS = new HashMap<String, TextAttributesKey>();

	@Nonnull
	public String getDisplayName()
	{
		return "Fantom";
	}

	@Nonnull
	public AttributesDescriptor[] getAttributeDescriptors()
	{
		return ATTRS;
	}

	@Nonnull
	public SyntaxHighlighter getHighlighter()
	{
		return new FanHighlighter();
	}

	@Nonnull
	public String getDemoText()
	{
		return "";
	}

	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap()
	{
		return ADDITIONAL_HIGHLIGHT_DESCRIPTORS;
	}
}
