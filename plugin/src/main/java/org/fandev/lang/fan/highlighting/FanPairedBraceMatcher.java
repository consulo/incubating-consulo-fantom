package org.fandev.lang.fan.highlighting;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.BracePair;
import consulo.language.Language;
import consulo.language.PairedBraceMatcher;
import org.fandev.lang.fan.FanLanguage;

import javax.annotation.Nonnull;

import static org.fandev.lang.fan.FanTokenTypes.*;

/**
 * Created by IntelliJ IDEA.
 * User: Dror
 * Date: Mar 13, 2009
 * Time: 3:54:16 PM
 */
@ExtensionImpl
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

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return FanLanguage.INSTANCE;
	}
}
