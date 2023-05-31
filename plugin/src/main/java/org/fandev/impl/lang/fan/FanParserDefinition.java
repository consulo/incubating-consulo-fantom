package org.fandev.impl.lang.fan;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IFileElementType;
import consulo.language.ast.TokenSet;
import consulo.language.file.FileViewProvider;
import consulo.language.lexer.Lexer;
import consulo.language.parser.ParserDefinition;
import consulo.language.parser.PsiParser;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.version.LanguageVersion;
import org.fandev.impl.lang.fan.parser.FanPsiCreator;
import org.fandev.impl.lang.fan.parsing.FanParser;
import org.fandev.impl.lang.fan.psi.impl.FanFileImpl;
import org.fandev.lang.fan.FanLanguage;

import javax.annotation.Nonnull;

import static consulo.language.parser.ParserDefinition.SpaceRequirements.MAY;

/**
 * @author Dror
 * @date Dec 11, 2008 11:50:55 PM
 */
@ExtensionImpl
public class FanParserDefinition implements ParserDefinition
{
	@Nonnull
	@Override
	public Language getLanguage()
	{
		return FanLanguage.INSTANCE;
	}

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
