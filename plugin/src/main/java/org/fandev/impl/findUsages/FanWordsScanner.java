package org.fandev.impl.findUsages;

import org.fandev.impl.lang.fan.FanParsingLexer;
import org.fandev.impl.lang.fan.FanTokenTypes;
import consulo.language.cacheBuilder.WordOccurrence;
import consulo.language.cacheBuilder.WordsScanner;
import consulo.language.lexer.Lexer;
import consulo.language.ast.IElementType;
import consulo.application.util.function.Processor;

/**
 * Date: Sep 18, 2009
 * Time: 12:23:07 AM
 *
 * @author Dror Bereznitsky
 */
public class FanWordsScanner implements WordsScanner
{
	private Lexer myLexer;

	public FanWordsScanner()
	{
		myLexer = new FanParsingLexer();
	}

	public void processWords(final CharSequence fileText, final Processor<WordOccurrence> processor)
	{
		myLexer.start(fileText, 0, fileText.length(), 0);
		WordOccurrence occurrence = null; // shared occurrence

		while(myLexer.getTokenType() != null)
		{
			final IElementType type = myLexer.getTokenType();
			if(type == FanTokenTypes.IDENTIFIER || FanTokenTypes.FAN_SYS_TYPE == type)
			{
				if(occurrence == null)
				{
					occurrence = new WordOccurrence(fileText, myLexer.getTokenStart(), myLexer.getTokenEnd(), WordOccurrence.Kind.CODE);
				}
				else
				{
					occurrence.init(fileText, myLexer.getTokenStart(), myLexer.getTokenEnd(), WordOccurrence.Kind.CODE);
				}
				if(!processor.process(occurrence))
				{
					return;
				}
			}
			else if(FanTokenTypes.COMMENTS.contains(type))
			{
				if(!stripWords(processor, fileText, myLexer.getTokenStart(), myLexer.getTokenEnd(), WordOccurrence.Kind.COMMENTS, occurrence))
				{
					return;
				}
			}
			else if(FanTokenTypes.STRING_LITERALS.contains(type))
			{
				if(!stripWords(processor, fileText, myLexer.getTokenStart(), myLexer.getTokenEnd(), WordOccurrence.Kind.LITERALS, occurrence))
				{
					return;
				}

				if(type == FanTokenTypes.STRING_LITERAL)
				{
					if(!stripWords(processor, fileText, myLexer.getTokenStart(), myLexer.getTokenEnd(), WordOccurrence.Kind.CODE, occurrence))
					{
						return;
					}
				}
			}

			myLexer.advance();
		}
	}

	private static boolean stripWords(final Processor<WordOccurrence> processor, final CharSequence tokenText, int from, int to,
									  final WordOccurrence.Kind kind, WordOccurrence occurrence)
	{
		// This code seems strange but it is more effective as Character.isJavaIdentifier_xxx_ is quite costly operation due to unicode
		int index = from;

		ScanWordsLoop:
		while(true)
		{
			while(true)
			{
				if(index == to)
				{
					break ScanWordsLoop;
				}
				char c = tokenText.charAt(index);
				if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') ||
						(Character.isJavaIdentifierStart(c) && c != '$'))
				{
					break;
				}
				index++;
			}
			int index1 = index;
			while(true)
			{
				index++;
				if(index == to)
				{
					break;
				}
				char c = tokenText.charAt(index);
				if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
				{
					continue;
				}
				if(!Character.isJavaIdentifierPart(c) || c == '$')
				{
					break;
				}
			}

			if(occurrence == null)
			{
				occurrence = new WordOccurrence(tokenText, index1, index, kind);
			}
			else
			{
				occurrence.init(tokenText, index1, index, kind);
			}
			if(!processor.process(occurrence))
			{
				return false;
			}
		}
		return true;
	}
}

