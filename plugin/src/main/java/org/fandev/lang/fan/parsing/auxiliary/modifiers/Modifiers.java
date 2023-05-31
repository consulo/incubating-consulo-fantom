package org.fandev.lang.fan.parsing.auxiliary.modifiers;

import static org.fandev.lang.fan.FanElementTypes.MODIFIERS;
import static org.fandev.lang.fan.FanTokenTypes.IDENTIFIER_TOKENS_SET;

import consulo.language.parser.PsiBuilder;
import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.util.ParserUtils;
import consulo.language.ast.IElementType;
import consulo.language.ast.TokenSet;

/**
 * @author Dror Bereznitsky
 * @date Jan 6, 2009 2:24:23 PM
 */
public class Modifiers
{

	public static TokenSet parse(final PsiBuilder builder, final DeclarationType stmtType)
	{
		TokenSet modifiers = TokenSet.create();

		ParserUtils.removeNls(builder);
		PsiBuilder.Marker modifiersMarker = builder.mark();

		while(!builder.eof())
		{
			// Either a modifier or the keyword/identifier...
			if(stmtType.getKeyword() != null)
			{
				if(stmtType.getKeyword().equals(builder.getTokenType()))
				{
					modifiersMarker.done(MODIFIERS);
					return modifiers;
				}
			}
			else
			{
				if(IDENTIFIER_TOKENS_SET.contains(builder.getTokenType()))
				{
					modifiersMarker.done(MODIFIERS);
					return modifiers;
				}
			}
			final IElementType possibleModifier = builder.getTokenType();
			if(!Modifier.parse(builder, stmtType))
			{
				if(FanTokenTypes.ALL_MODIFIERS.contains(possibleModifier))
				{
					// illegal access modifier
					final String tokenText = builder.getTokenText();
					builder.error(FanBundle.message("illegal.modifier", tokenText, stmtType));
					builder.advanceLexer();
				}
				else
				{
					modifiersMarker.done(MODIFIERS);
					break;
				}
			}
			else
			{
				modifiers = TokenSet.orSet(modifiers, TokenSet.create(possibleModifier));
			}
		}
		return modifiers;
	}
}
