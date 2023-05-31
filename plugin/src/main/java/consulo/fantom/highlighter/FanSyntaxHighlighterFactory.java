package consulo.fantom.highlighter;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.highlight.SingleLazyInstanceSyntaxHighlighterFactory;
import consulo.language.editor.highlight.SyntaxHighlighter;
import org.fandev.lang.fan.FanLanguage;
import org.fandev.lang.fan.highlighting.FanHighlighter;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 2018-09-10
 */
@ExtensionImpl
public class FanSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory
{
	@Nonnull
	@Override
	protected SyntaxHighlighter createHighlighter()
	{
		return new FanHighlighter();
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return FanLanguage.INSTANCE;
	}
}
