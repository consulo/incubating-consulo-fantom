package consulo.fantom.highlighter;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.highlighting.FanHighlighter;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;

/**
 * @author VISTALL
 * @since 2018-09-10
 */
public class FanSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory
{
	@Nonnull
	@Override
	protected SyntaxHighlighter createHighlighter()
	{
		return new FanHighlighter();
	}
}
