package org.fandev.java.debugger;

import com.intellij.java.debugger.PositionManager;
import com.intellij.java.debugger.PositionManagerFactory;
import com.intellij.java.debugger.engine.DebugProcess;
import consulo.annotation.component.ExtensionImpl;

import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 03.02.14
 */
@ExtensionImpl
public class FanPositionManagerFactory extends PositionManagerFactory
{
	@Nullable
	@Override
	public PositionManager createPositionManager(DebugProcess debugProcess)
	{
		return new FanPositionManager(debugProcess);
	}
}
