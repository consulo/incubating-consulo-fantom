package org.fandev.lang.fan.parsing.statements.typeDefinitions.typeDefs;

import static org.fandev.lang.fan.FanTokenTypes.CLASS_KEYWORD;
import static org.fandev.lang.fan.FanTokenTypes.COLON;
import static org.fandev.lang.fan.FanTokenTypes.FAN_SYS_TYPE;
import static org.fandev.lang.fan.FanTokenTypes.IDENTIFIER_TOKENS_SET;

import org.fandev.lang.fan.FanBundle;
import org.fandev.lang.fan.FanElementTypes;
import org.fandev.lang.fan.parsing.auxiliary.modifiers.Modifiers;
import org.fandev.lang.fan.parsing.statements.declaration.DeclarationType;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.InheritanceClause;
import org.fandev.lang.fan.parsing.statements.typeDefinitions.blocks.ClassBlock;
import org.fandev.lang.fan.parsing.util.ParserUtils;
import consulo.language.parser.PsiBuilder;

/**
 * @author Dror Bereznitsky
 * @date Jan 6, 2009 4:43:55 PM
 */
public class ClassDefinition
{
	public static boolean parse(final PsiBuilder builder)
	{
		Modifiers.parse(builder, DeclarationType.CLASS);

		if(!ParserUtils.getToken(builder, CLASS_KEYWORD))
		{
			builder.error(FanBundle.message("keywords.expected", CLASS_KEYWORD.toString()));
			return false;
		}
		ParserUtils.removeNls(builder);

		// Class name can be one of the built in types in case of the Fan language sources
		if(!IDENTIFIER_TOKENS_SET.contains(builder.getTokenType()))
		{
			builder.error(FanBundle.message("identifier.expected"));
			return false;
		}
		final boolean isBuiltInType = FAN_SYS_TYPE == builder.getTokenType();

		final PsiBuilder.Marker idMark = builder.mark();
		builder.advanceLexer();
		idMark.done(FanElementTypes.NAME_ELEMENT);
		ParserUtils.removeNls(builder);

		if(COLON.equals(builder.getTokenType()))
		{
			InheritanceClause.parse(builder);
			ParserUtils.removeNls(builder);
		}
		return ClassBlock.parse(builder, isBuiltInType);
	}
}
