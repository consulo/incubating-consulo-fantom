package org.fandev.lang.fan.highlighting;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import org.fandev.lang.fan.FanSupportLoader;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;

/**
 * @author Dror Bereznitsky
 * @date Dec 22, 2008 10:09:15 PM
 */
public class FanColorsAndFontsPage implements ColorSettingsPage
{
	private static final AttributesDescriptor[] ATTRS = {};
	@NonNls
	private static final Map<String, TextAttributesKey> ADDITIONAL_HIGHLIGHT_DESCRIPTORS = new HashMap<String, TextAttributesKey>();

	@NotNull
	public String getDisplayName()
	{
		return "Fantom";
	}

	@NotNull
	public AttributesDescriptor[] getAttributeDescriptors()
	{
		return ATTRS;
	}

	@NotNull
	public ColorDescriptor[] getColorDescriptors()
	{
		return ColorDescriptor.EMPTY_ARRAY;
	}

	@NotNull
	public SyntaxHighlighter getHighlighter()
	{
		return SyntaxHighlighter.PROVIDER.create(FanSupportLoader.FAN, null, null);
	}

	@NotNull
	public String getDemoText()
	{
		return "";
	}

	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap()
	{
		return ADDITIONAL_HIGHLIGHT_DESCRIPTORS;
	}
}
