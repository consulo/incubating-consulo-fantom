package org.fandev.impl.lang.fan;

import java.io.Reader;

import consulo.language.lexer.FlexAdapter;
import consulo.language.lexer.FlexLexer;

/**
 * @author Dror Bereznitsky
 * @date Dec 21, 2008 4:01:40 PM
 */
public class FanFlexAdapter extends FlexAdapter
{
	public FanFlexAdapter()
	{
		this(new _FanLexer((Reader) null));
	}

	public FanFlexAdapter(final FlexLexer lexer)
	{
		super(lexer);
		assert lexer instanceof _FanLexer;
	}
}
