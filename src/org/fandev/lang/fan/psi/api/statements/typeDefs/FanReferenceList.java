package org.fandev.lang.fan.psi.api.statements.typeDefs;

import org.fandev.lang.fan.psi.api.types.FanCodeReferenceElement;
import org.fandev.lang.fan.psi.stubs.FanReferenceListStub;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;

/**
 * Date: Mar 18, 2009
 * Time: 10:54:49 PM
 *
 * @author Dror Bereznitsky
 */
public interface FanReferenceList extends StubBasedPsiElement<FanReferenceListStub>, PsiElement
{
	FanCodeReferenceElement[] getReferenceElements();
}
