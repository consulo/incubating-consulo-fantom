package org.fandev.impl.lang.fan.psi.stubs.index;

import org.fandev.impl.lang.fan.psi.api.statements.typeDefs.FanReferenceList;
import consulo.language.psi.stub.StringStubIndexExtension;
import consulo.language.psi.stub.StubIndexKey;

/**
 * Created by IntelliJ IDEA.
 * User: Dror
 * Date: Mar 20, 2009
 * Time: 4:22:27 PM
 */
public class FanDirectInheritorsIndex extends StringStubIndexExtension<FanReferenceList>
{
	public static final StubIndexKey<String, FanReferenceList> KEY = StubIndexKey.createIndexKey("fan.class.super");

	public StubIndexKey<String, FanReferenceList> getKey()
	{
		return KEY;
	}

}
