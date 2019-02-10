package org.fandev.lang.fan;

import static com.intellij.lang.ParserDefinition.SpaceRequirements.MAY;

import javax.annotation.Nonnull;

import org.fandev.lang.fan.parser.FanPsiCreator;
import org.fandev.lang.fan.parsing.FanParser;
import org.fandev.lang.fan.psi.impl.FanFileImpl;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import consulo.lang.LanguageVersion;

/**
 * @author Dror
 * @date Dec 11, 2008 11:50:55 PM
 */
public class FanParserDefinition implements ParserDefinition
{
	@Override
	@Nonnull
	public Lexer createLexer(@Nonnull LanguageVersion languageVersion)
	{
		return new FanParsingLexer();
	}

	@Nonnull
	@Override
	public PsiParser createParser(@Nonnull LanguageVersion languageVersion)
	{
		return new FanParser();
	}

	@Nonnull
	@Override
	public IFileElementType getFileNodeType()
	{
		return FanElementTypes.FILE;
	}

	@Override
	@Nonnull
	public TokenSet getWhitespaceTokens(@Nonnull LanguageVersion languageVersion)
	{
		return TokenSet.create(FanTokenTypes.WHITE_SPACE);
	}

	@Override
	@Nonnull
	public TokenSet getCommentTokens(@Nonnull LanguageVersion languageVersion)
	{
		return FanTokenTypes.COMMENTS;
	}

	@Override
	@Nonnull
	public TokenSet getStringLiteralElements(@Nonnull LanguageVersion languageVersion)
	{
		return FanTokenTypes.STRING_LITERALS;
	}

	@Override
	@Nonnull
	public PsiElement createElement(final ASTNode astNode)
	{
		return FanPsiCreator.createElement(astNode);
	}

	@Override
	public PsiFile createFile(final FileViewProvider fileViewProvider)
	{
		return new FanFileImpl(fileViewProvider);
	}

	@Override
	public SpaceRequirements spaceExistanceTypeBetweenTokens(final ASTNode astNode, final ASTNode astNode1)
	{
		return MAY;
	}
}
