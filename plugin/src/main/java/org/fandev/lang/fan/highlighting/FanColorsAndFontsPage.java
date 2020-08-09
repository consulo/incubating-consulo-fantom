package org.fandev.lang.fan.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dror Bereznitsky
 * @date Dec 22, 2008 10:09:15 PM
 */
public class FanColorsAndFontsPage implements ColorSettingsPage
{
	private static final AttributesDescriptor[] ATTRS = {};
	@NonNls
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
	public ColorDescriptor[] getColorDescriptors()
	{
		return ColorDescriptor.EMPTY_ARRAY;
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
