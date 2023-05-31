package org.fandev.lang.fan.highlighting;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.CodeDocumentationAwareCommenter;
import consulo.language.Language;
import jakarta.annotation.Nonnull;
import org.fandev.lang.fan.FanLanguage;
import org.fandev.lang.fan.FanTokenTypes;
import consulo.language.psi.PsiComment;
import consulo.language.ast.IElementType;

/**
 * Created by IntelliJ IDEA.
 * User: Dror
 * Date: Mar 13, 2009
 * Time: 9:26:52 AM
 */
@ExtensionImpl
public class FanCommenter implements CodeDocumentationAwareCommenter
{
	@Override
	public String getLineCommentPrefix()
	{
		return "//";
	}

	@Override
	public String getBlockCommentPrefix()
	{
		return "/*";
	}

	@Override
	public String getBlockCommentSuffix()
	{
		return "*/";
	}

	@Override
	public IElementType getLineCommentTokenType()
	{
		return FanTokenTypes.END_OF_LINE_COMMENT;
	}

	@Override
	public IElementType getBlockCommentTokenType()
	{
		return FanTokenTypes.C_STYLE_COMMENT;
	}

	@Override
	public IElementType getDocumentationCommentTokenType()
	{
		return FanTokenTypes.FANDOC_LINE_COMMENT;
	}

	@Override
	public String getDocumentationCommentPrefix()
	{
		return null;
	}

	@Override
	public String getDocumentationCommentLinePrefix()
	{
		return "**";
	}

	@Override
	public String getDocumentationCommentSuffix()
	{
		return null;
	}

	@Override
	public boolean isDocumentationComment(PsiComment element)
	{
		return false;
	}

	@Override
	public String getCommentedBlockCommentPrefix()
	{
		return null;
	}

	@Override
	public String getCommentedBlockCommentSuffix()
	{
		return null;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return FanLanguage.INSTANCE;
	}
}
