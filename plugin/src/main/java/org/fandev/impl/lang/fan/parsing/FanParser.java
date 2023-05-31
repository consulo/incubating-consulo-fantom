package org.fandev.impl.lang.fan.parsing;

import consulo.component.ProcessCanceledException;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.parser.PsiBuilder;
import consulo.language.parser.PsiParser;
import consulo.language.version.LanguageVersion;
import consulo.logging.Logger;
import org.fandev.impl.lang.fan.parsing.topLevel.CompilationUnit;
import org.fandev.lang.fan.FanBundle;

import javax.annotation.Nonnull;

/**
 * @author Dror
 * @date Dec 11, 2008 11:54:37 PM
 */
public class FanParser implements PsiParser
{
	private final static Logger logger = Logger.getInstance(FanParser.class);

	@Override
	@Nonnull
	public ASTNode parse(@Nonnull final IElementType root, @Nonnull final PsiBuilder psiBuilder, @Nonnull LanguageVersion languageVersion)
	{
		psiBuilder.setDebugMode(true);
		final PsiBuilder.Marker rootMarker = psiBuilder.mark();

		CompilationUnit.parse(psiBuilder);

		// Make sure we ate it all
		if(!psiBuilder.eof())
		{
			final PsiBuilder.Marker errorMark = psiBuilder.mark();
			while(!psiBuilder.eof())
			{
				psiBuilder.advanceLexer();
			}
			errorMark.error(FanBundle.message("typedef.expected"));
		}

		rootMarker.done(root);
		try
		{
			return psiBuilder.getTreeBuilt();
		}
		catch(ProcessCanceledException e)
		{
			throw e;
		}
		catch(Throwable t)
		{
			final StringBuilder sb = new StringBuilder();
			while(!psiBuilder.eof())
			{
				sb.append(psiBuilder.getTokenText());
				psiBuilder.advanceLexer();
			}
			logger.error("Parsing error, current offset is: " + psiBuilder.getCurrentOffset() + " Remaining text is: " +
					sb.toString(), t);
			// Notice - this puts the application in an very unstable situation !!!
			// The action that starts the parsing never finish and so other write actions are blocked for good
			// ApplicationManager.getApplication().
			throw new RuntimeException(t);
		}
	}
}
