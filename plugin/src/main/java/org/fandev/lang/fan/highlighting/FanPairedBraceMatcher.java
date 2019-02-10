package org.fandev.lang.fan.highlighting;

import static org.fandev.lang.fan.FanTokenTypes.LBRACE;
import static org.fandev.lang.fan.FanTokenTypes.LBRACKET;
import static org.fandev.lang.fan.FanTokenTypes.LPAR;
import static org.fandev.lang.fan.FanTokenTypes.RBRACE;
import static org.fandev.lang.fan.FanTokenTypes.RBRACKET;
import static org.fandev.lang.fan.FanTokenTypes.RPAR;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;

/**
 * Created by IntelliJ IDEA.
 * User: Dror
 * Date: Mar 13, 2009
 * Time: 3:54:16 PM
 */
public class FanPairedBraceMatcher implements PairedBraceMatcher
{
	private static final BracePair[] PAIRS = new BracePair[]{
			new BracePair(LBRACE, RBRACE, true),
			new BracePair(LPAR, RPAR, false),
			new BracePair(LBRACKET, RBRACKET, false)
	};

	public BracePair[] getPairs()
	{
		return PAIRS;
	}

	public boolean isPairedBracesAllowedBeforeType(@Nonnull final IElementType lbraceType, @Nullable final IElementType contextType)
	{
		return true;
	}

	public int getCodeConstructStart(final PsiFile file, final int openingBraceOffset)
	{
		return openingBraceOffset;
	}
}
