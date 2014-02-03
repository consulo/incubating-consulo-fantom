package org.fandev.lang.fan;

import org.fandev.lang.fan.highlighting.FanHighlighter;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;

/**
 * @author Dror
 * @date Dec 12, 2008 12:04:58 AM
 */
public class FanLanguage extends Language
{
	public static final FanLanguage INSTANCE = new FanLanguage();

	private FanLanguage()
	{
		super("Fantom", "text/fan", "application/fan");

		SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory()
		{
			@Override
			@NotNull
			protected SyntaxHighlighter createHighlighter()
			{
				return new FanHighlighter();
			}
		});
	}
}
