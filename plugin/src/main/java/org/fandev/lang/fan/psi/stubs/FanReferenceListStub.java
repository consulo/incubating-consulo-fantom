package org.fandev.lang.fan.psi.stubs;

import org.fandev.lang.fan.psi.api.statements.typeDefs.FanReferenceList;
import com.intellij.psi.stubs.StubElement;

/**
 * Date: Mar 18, 2009
 * Time: 10:56:18 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanReferenceListStub extends StubElement<FanReferenceList>
{
	String[] getBaseClasses();
}
