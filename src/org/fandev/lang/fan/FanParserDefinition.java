package org.fandev.lang.fan;

import static com.intellij.lang.ParserDefinition.SpaceRequirements.MAY;

import org.fandev.lang.fan.parser.FanPsiCreator;
import org.fandev.lang.fan.parsing.FanParser;
import org.fandev.lang.fan.psi.impl.FanFileImpl;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.LanguageVersion;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author Dror
 * @date Dec 11, 2008 11:50:55 PM
 */
public class FanParserDefinition implements ParserDefinition
{
	@Override
	@NotNull
	public Lexer createLexer(final Project project, @NotNull LanguageVersion languageVersion)
	{
		return new FanParsingLexer();
	}

	@NotNull
	@Override
	public PsiParser createParser(final Project project, @NotNull LanguageVersion languageVersion)
	{
		return new FanParser();
	}

	@NotNull
	@Override
	public IFileElementType getFileNodeType()
	{
		return FanElementTypes.FILE;
	}

	@Override
	@NotNull
	public TokenSet getWhitespaceTokens(@NotNull LanguageVersion languageVersion)
	{
		return TokenSet.create(FanTokenTypes.WHITE_SPACE);
	}

	@Override
	@NotNull
	public TokenSet getCommentTokens(@NotNull LanguageVersion languageVersion)
	{
		return FanTokenTypes.COMMENTS;
	}

	@Override
	@NotNull
	public TokenSet getStringLiteralElements(@NotNull LanguageVersion languageVersion)
	{
		return FanTokenTypes.STRING_LITERALS;
	}

	@Override
	@NotNull
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
