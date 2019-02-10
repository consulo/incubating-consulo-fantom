package org.fandev.debugger;

import javax.annotation.Nullable;
import com.intellij.debugger.PositionManager;
import com.intellij.debugger.PositionManagerFactory;
import com.intellij.debugger.engine.DebugProcess;

/**
 * @author VISTALL
 * @since 03.02.14
 */
public class FanPositionManagerFactory extends PositionManagerFactory
{
	@Nullable
	@Override
	public PositionManager createPositionManager(DebugProcess debugProcess)
	{
		return new FanPositionManager(debugProcess);
	}
}
