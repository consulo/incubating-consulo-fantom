/*
 * Copyright 2000-2007 JetBrains s.r.o.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fandev.impl.lang.fan.parsing.expression.arithmetic;

import static org.fandev.impl.lang.fan.FanElementTypes.SHIFT_EXPR;
import static org.fandev.impl.lang.fan.FanTokenTypes.GTGT;
import static org.fandev.impl.lang.fan.FanTokenTypes.LTLT;

import org.fandev.impl.lang.fan.parsing.expression.logical.SeparatorRepeatExpression;
import consulo.language.parser.PsiBuilder;
import consulo.language.ast.TokenSet;

/**
 * @author ilyas
 */
public class ShiftExpression extends SeparatorRepeatExpression
{
	private static final ShiftExpression instance = new ShiftExpression();

	public ShiftExpression()
	{
		super(SHIFT_EXPR, TokenSet.create(GTGT, LTLT));
	}

	public boolean innerParse(final PsiBuilder builder, final TokenSet stopper)
	{
		return AdditiveExpression.parse(builder, stopper);
	}

	public static boolean parse(final PsiBuilder builder, final TokenSet stopper)
	{
		return instance.parseThis(builder, stopper);
	}
}