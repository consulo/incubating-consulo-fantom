/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fandev.lang.fan.psi.impl.statements.blocks;

import org.fandev.lang.fan.FanTokenTypes;
import org.fandev.lang.fan.psi.api.statements.blocks.FanPsiCodeBlock;
import org.fandev.lang.fan.psi.api.statements.typeDefs.members.FanStatement;
import javax.annotation.Nonnull;
import consulo.language.impl.psi.ASTWrapperPsiElement;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiElement;

/**
 * @author freds
 * @date Feb 18, 2009
 */
public class FanPsiCodeBlockImpl extends ASTWrapperPsiElement implements FanPsiCodeBlock
{
	public FanPsiCodeBlockImpl(@Nonnull final ASTNode node)
	{
		super(node);
	}

	@Override
	@Nonnull
	public FanStatement[] getStatements()
	{
		return findChildrenByClass(FanStatement.class);
	}

	public PsiElement getFirstBodyElement()
	{
		return null;
	}

	public PsiElement getLastBodyElement()
	{
		return null;
	}

	@Override
	public PsiElement getLeftBrace()
	{
		return findChildByType(FanTokenTypes.LBRACE);
	}

	@Override
	public PsiElement getRightBrace()
	{
		return findChildByType(FanTokenTypes.RBRACE);
	}
}
