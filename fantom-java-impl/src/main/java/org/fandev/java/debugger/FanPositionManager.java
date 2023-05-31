package org.fandev.java.debugger;

import com.intellij.java.debugger.NoDataException;
import com.intellij.java.debugger.PositionManager;
import com.intellij.java.debugger.SourcePosition;
import com.intellij.java.debugger.engine.DebugProcess;
import com.intellij.java.debugger.impl.engine.CompoundPositionManager;
import com.intellij.java.debugger.impl.engine.DebugProcessImpl;
import com.intellij.java.debugger.requests.ClassPrepareRequestor;
import consulo.application.ApplicationManager;
import consulo.application.util.function.Computable;
import consulo.internal.com.sun.jdi.AbsentInformationException;
import consulo.internal.com.sun.jdi.Location;
import consulo.internal.com.sun.jdi.ReferenceType;
import consulo.internal.com.sun.jdi.request.ClassPrepareRequest;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.project.Project;
import org.fandev.index.FanIndex;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: Sep 2, 2009
 * Time: 11:40:51 PM
 *
 * @author Dror Bereznitsky
 */
public class FanPositionManager implements PositionManager
{
	private final DebugProcess myDebugProcess;

	public FanPositionManager(final DebugProcess debugProcess)
	{
		myDebugProcess = debugProcess;
	}

	public DebugProcess getDebugProcess()
	{
		return myDebugProcess;
	}

	@Override
	public SourcePosition getSourcePosition(final Location location) throws NoDataException
	{
		if(location == null)
		{
			throw new NoDataException();
		}

		final PsiFile psiFile = getPsiFileByLocation(getDebugProcess().getProject(), location);
		if(psiFile == null)
		{
			throw new NoDataException();
		}

		int lineNumber = calcLineIndex(location);
		if(lineNumber < 0)
		{
			throw new NoDataException();
		}
		return SourcePosition.createFromLine(psiFile, lineNumber);
	}

	@Override
	@Nonnull
	public List<ReferenceType> getAllClasses(final SourcePosition classPosition) throws NoDataException
	{
		return ApplicationManager.getApplication().runReadAction(new Computable<List<ReferenceType>>()
		{
			@Override
			public List<ReferenceType> compute()
			{
				final List<ReferenceType> result = new ArrayList<ReferenceType>();
				final PsiFile file = classPosition.getFile();
				if(file instanceof FanFile)
				{
					final FanTypeDefinition[] typeDefinitions = ((FanFile) file).getTypeDefinitions();
					for(final FanTypeDefinition def : typeDefinitions)
					{
						final String enclosingName = def.getJavaQualifiedName();
						result.addAll(myDebugProcess.getVirtualMachineProxy().classesByName(enclosingName));
					}
				}
				return result;
			}
		});
	}

	@Override
	@Nonnull
	public List<Location> locationsOfLine(final ReferenceType type, final SourcePosition position) throws NoDataException
	{
		try
		{
			int line = position.getLine() + 1;
			final List<Location> locations = getDebugProcess().getVirtualMachineProxy().versionHigher("1.4") ? type.locationsOfLine(DebugProcessImpl
					.JAVA_STRATUM, null, line) : type.locationsOfLine(line);
			if(locations == null || locations.isEmpty())
			{
				throw new NoDataException();
			}
			return locations;
		}
		catch(AbsentInformationException e)
		{
			throw new NoDataException();
		}
	}

	@Override
	public ClassPrepareRequest createPrepareRequest(final ClassPrepareRequestor requestor, final SourcePosition position) throws NoDataException
	{
		String qName;
		ClassPrepareRequestor waitRequestor;
		String waitPrepareFor = "";

		final FanTypeDefinition typeDefinition = findEnclosingTypeDefinition(position);
		if(typeDefinition == null)
		{
			return null;
		}
		qName = typeDefinition.getJavaQualifiedName();

		waitPrepareFor = qName;
		waitRequestor = new ClassPrepareRequestor()
		{
			@Override
			public void processClassPrepare(final DebugProcess debuggerProcess, final ReferenceType referenceType)
			{
				final CompoundPositionManager positionManager = ((DebugProcessImpl) debuggerProcess).getPositionManager();
				if(positionManager.locationsOfLine(referenceType, position).size() > 0)
				{
					requestor.processClassPrepare(debuggerProcess, referenceType);
				}
				else
				{
					final List<ReferenceType> positionClasses = positionManager.getAllClasses(position);
					if(positionClasses.contains(referenceType))
					{
						requestor.processClassPrepare(debuggerProcess, referenceType);
					}
				}
			}
		};
		return myDebugProcess.getRequestsManager().createClassPrepareRequest(waitRequestor, waitPrepareFor);
	}

	@Nullable
	private PsiFile getPsiFileByLocation(final Project project, final Location location)
	{
		if(location == null)
		{
			return null;
		}

		final ReferenceType refType = location.declaringType();
		if(refType == null)
		{
			return null;
		}

		// Currently deal only with Fan types
		if(!refType.name().startsWith("fan"))
		{
			return null;
		}

		String name = refType.name().substring(refType.name().lastIndexOf(".") + 1);
		final String[] names = name.split("\\$"); //Closures and functions
		name = names[0];
		return project.getComponent(FanIndex.class).getFanFileByTypeName(name);
	}

	private int calcLineIndex(final Location location)
	{
		if(location == null)
		{
			return -1;
		}

		try
		{
			return location.lineNumber() - 1;
		}
		catch(InternalError e)
		{
			return -1;
		}
	}

	private FanTypeDefinition findEnclosingTypeDefinition(final SourcePosition position)
	{
		final PsiFile file = position.getFile();
		if(!(file instanceof FanFile))
		{
			return null;
		}
		final PsiElement element = file.findElementAt(position.getOffset());
		if(element == null)
		{
			return null;
		}
		return PsiTreeUtil.getParentOfType(element, FanTypeDefinition.class);
	}
}
