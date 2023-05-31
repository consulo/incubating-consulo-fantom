package org.fandev.compiler;

import consulo.annotation.component.ExtensionImpl;
import consulo.application.util.SystemInfo;
import consulo.compiler.CompileContext;
import consulo.compiler.CompilerMessageCategory;
import consulo.compiler.TranslatingCompiler;
import consulo.compiler.scope.CompileScope;
import consulo.ide.util.ZipUtil;
import consulo.module.Module;
import consulo.process.event.ProcessAdapter;
import consulo.process.event.ProcessEvent;
import consulo.process.event.ProcessListener;
import consulo.process.internal.OSProcessHandler;
import consulo.project.Project;
import consulo.util.collection.Chunk;
import consulo.util.dataholder.Key;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;
import jakarta.inject.Inject;
import org.fandev.lang.fan.FanFileType;
import org.fandev.sdk.FanSdkType;
import org.fandev.utils.FanUtil;
import org.fandev.utils.VirtualFileUtil;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Dror Bereznitsky
 * @date Jan 25, 2009 12:08:47 AM
 */
@ExtensionImpl
public class FanCompiler implements TranslatingCompiler
{
	private static final String FAN_COMPILER = "Fantom compiler";

	public boolean isCompilableFile(final VirtualFile file, final CompileContext context)
	{
		return FanFileType.INSTANCE.equals(file.getFileType());
	}

	public void compile(final CompileContext context, final Chunk<Module> moduleChunk, final VirtualFile[] files,
						final OutputSink sink)
	{
		/*Set<VirtualFile> toRecompile = new HashSet<VirtualFile>();
		Set<TranslatingCompiler.OutputItem> successfullyCompiled = new HashSet<TranslatingCompiler.OutputItem>();
		final Set<Module> modulesToBuild = new HashSet<Module>();
		final Map<Module, List<VirtualFile>> moduleToFile = new HashMap<Module, List<VirtualFile>>();

		for(final VirtualFile fileToCompile : files)
		{
			final Module moduleToBuild = context.getModuleByFile(fileToCompile);
			modulesToBuild.add(moduleToBuild);
			if(!moduleToFile.containsKey(moduleToBuild))
			{
				moduleToFile.put(moduleToBuild, new ArrayList<VirtualFile>());
			}
			moduleToFile.get(moduleToBuild).add(fileToCompile);
		}

		for(final Module moduleToBuild : modulesToBuild)
		{
			final Sdk moduleSdk = FanUtil.getFanSdk(moduleToBuild);
			if(moduleSdk == null)
			{
				// Not a fan module
				continue;
			}
			FanUtil.setFanHome(moduleSdk);
			final String fanLauncherPath = moduleSdk.getHomePath() + File.separator + FanSdkType.getFanLauncherPath();

			final VirtualFile buildVirtualFile = FanModuleSettings.getInstance(moduleToBuild).getBuildScript();
			if(VirtualFileUtil.fileExists(buildVirtualFile))
			{
				// Build Fan bytecode (.pod archive)
				Process process;
				try
				{
					//TODO [Dror] reuese from FanScriptRunConfiguration
					process = Runtime.getRuntime().exec(new String[]{
							fanLauncherPath,
							buildVirtualFile.getPath(),
							"-v",
							"compile"
					});
					final OSProcessHandler handler = new OSProcessHandler(process, "");
					handler.addProcessListener(new FanCompileProcessListener(context, moduleToBuild, toRecompile));
					handler.startNotify();
				}
				catch(IOException e)
				{
					e.printStackTrace();
					// Unable to build this module
					context.addMessage(CompilerMessageCategory.ERROR, "Failed to compile module", null, -1, -1);
					toRecompile.addAll(moduleToFile.get(moduleToBuild));
				}

			}
			else
			{
				// Unable to build this module
				context.addMessage(CompilerMessageCategory.ERROR, "Failed to compile module, could not find build file: " + FanModuleSettings
						.getInstance(moduleToBuild).getBuildScriptPath(), null, -1, -1);
				toRecompile.addAll(moduleToFile.get(moduleToBuild));
			}
			sink.add(moduleSdk.getHomePath() + "/lib", successfullyCompiled, toRecompile.toArray(new VirtualFile[0]));
			toRecompile = new HashSet<VirtualFile>();
			successfullyCompiled = new HashSet<TranslatingCompiler.OutputItem>();
		}   */
	}

	@Nonnull
	@Override
	public FileType[] getInputFileTypes()
	{
		return new FileType[0];
	}

	@Nonnull
	@Override
	public FileType[] getOutputFileTypes()
	{
		return new FileType[0];
	}

	@Nonnull
	public String getDescription()
	{
		return FAN_COMPILER;
	}

	public boolean validateConfiguration(final CompileScope compileScope)
	{
		/*final VirtualFile[] files = compileScope.getFiles(FanFileType.INSTANCE, true);
		if(files.length == 0)
		{
			return true;
		}

		final Set<Module> modules = new HashSet<Module>();
		for(final VirtualFile file : files)
		{
			final ProjectRootManager rootManager = ProjectRootManager.getInstance(myProject);
			final Module module = rootManager.getFileIndex().getModuleForFile(file);
			if(module != null)
			{
				modules.add(module);
			}
		}

		final Set<Module> nojdkModules = new HashSet<Module>();
		for(final Module module : compileScope.getAffectedModules())
		{
			if(!(module.getModuleType() instanceof JavaModuleType))
			{
				continue;
			}
			final Sdk sdk = ModuleRootManager.getInstance(module).getSdk();
			if(sdk == null || !(sdk.getSdkType() instanceof FanSdkType))
			{
				nojdkModules.add(module);
			}
		}

		if(!nojdkModules.isEmpty())
		{
			final Module[] noJdkArray = nojdkModules.toArray(new Module[0]);
			if(noJdkArray.length == 1)
			{
				Messages.showErrorDialog(myProject, FanBundle.message("cannot.compile.fan.files.no.sdk", noJdkArray[0].getName()),
						FanBundle.message("cannot.compile"));
			}
			else
			{
				final StringBuffer modulesList = new StringBuffer();
				for(int i = 0; i < noJdkArray.length; i++)
				{
					if(i > 0)
					{
						modulesList.append(", ");
					}
					modulesList.append(noJdkArray[i].getName());
				}
				Messages.showErrorDialog(myProject, FanBundle.message("cannot.compile.fan.files.no.sdk.mult", modulesList.toString()),
						FanBundle.message("cannot.compile"));
			}
			return false;
		}    */

		return true;
	}

	class FanCompileProcessListener implements ProcessListener
	{
		private boolean finishedSuccessfuly = false;
		private String podName;
		private final Module module;
		private final CompileContext context;
		private final String moduleRootPath;
		private final String projectRoot;
		private final Set<VirtualFile> toRecompile;
		private final List<String> jstubParams;

		FanCompileProcessListener(final CompileContext context, final Module module, final Set<VirtualFile> toRecompile)
		{
			this.context = context;
			this.module = module;
			this.jstubParams = new ArrayList<String>();
			this.jstubParams.add(VirtualFileUtil.buildUrl(FanUtil.getFanSdk(module).getHomePath(), FanSdkType.getJStubPath()));
			if(SystemInfo.isUnix)
			{
				jstubParams.add("jstub");
			}
			this.moduleRootPath = context.getModuleOutputDirectory(module).getPath();
			jstubParams.add("-v");
			jstubParams.add("-d");
			jstubParams.add(moduleRootPath);
			this.projectRoot = new File(context.getProject().getBaseDir().getPath()).getAbsolutePath();
			this.toRecompile = toRecompile;
		}

		public void startNotified(final ProcessEvent event)
		{
		}

		//TODO - replace this with a more elegant solution
		public void processTerminated(final ProcessEvent event)
		{
			if(event.getExitCode() == 0 && finishedSuccessfuly)
			{
				Process process;
				try
				{
					final List<String> params = new ArrayList<String>(jstubParams);
					params.add(podName);
					process = Runtime.getRuntime().exec(params.toArray(new String[0]));
					final OSProcessHandler handler = new OSProcessHandler(process, "");
					handler.addProcessListener(new ProcessAdapter()
					{
						private int classesCreated = 0;

						@Override
						public void onTextAvailable(final ProcessEvent event, final Key outputType)
						{
							if(event.getText().contains(".class"))
							{
								classesCreated++;
							}
						}

						@Override
						public void processTerminated(final ProcessEvent event)
						{

							if(event.getExitCode() != 0)
							{
								context.addMessage(CompilerMessageCategory.ERROR, "Failed to create Java stubs for pod " + podName, null, -1, -1);
							}
							else
							{
								final File generatedJarFile = new File(VirtualFileUtil.buildUrl(moduleRootPath, podName + ".jar"));
								final File outFolder = new File(VirtualFileUtil.buildUrl(moduleRootPath, "classes"));
								if(generatedJarFile.exists())
								{
									outFolder.mkdirs();
									try
									{
										ZipUtil.extract(generatedJarFile, outFolder, null);
									}
									catch(IOException e)
									{
										context.addMessage(CompilerMessageCategory.ERROR, "Failed to extract classes from " + generatedJarFile
												.getAbsolutePath(), null, -1, -1);
									}
								}
								context.addMessage(CompilerMessageCategory.INFORMATION, "Java stubs for pod '" + podName + "' created successfully",
										null, -1, -1);
								context.addMessage(CompilerMessageCategory.STATISTICS, classesCreated + " classes created", null, -1, -1);
							}
						}
					});
					handler.startNotify();
				}
				catch(IOException e)
				{
					context.addMessage(CompilerMessageCategory.ERROR, "Failed to create Java stubs for pod '" + podName + "' : " + e.getMessage(),
							null, -1, -1);
				}
			}
		}

		public void processWillTerminate(final ProcessEvent event, final boolean willBeDestroyed)
		{
		}

		public void onTextAvailable(final ProcessEvent event, final Key outputType)
		{
			final String eventText = event.getText();
			if(eventText.contains("BUILD SUCCESS"))
			{
				finishedSuccessfuly = true;
				context.addMessage(CompilerMessageCategory.INFORMATION, "Compilation of Pod '" + podName + "' finished successfuly", null, -1, -1);
			}
			else if(eventText.contains("BUILD FAILED"))
			{
				finishedSuccessfuly = false;
				context.addMessage(CompilerMessageCategory.ERROR, "Compilation of Pod '" + podName + "' failed", null, -1, -1);
			}
			else if(eventText.contains("Compile"))
			{
				//TODO this is a really ugly way to get the pod name. need something generic
				final int indexOfLefttBr = eventText.indexOf("[");
				final int indexOfRightBr = eventText.indexOf("]");
				podName = indexOfLefttBr > -1 && indexOfRightBr > indexOfLefttBr ? eventText.substring(indexOfLefttBr + 1,
						indexOfRightBr) : "Unknown";
				context.addMessage(CompilerMessageCategory.INFORMATION, "Compiling Pod '" + podName + "'", null, -1, -1);
			}
			else if(eventText.startsWith(projectRoot))
			{
				// <FILE>(<ROW>,<COLUMN>): <ERROR>
				final String errorFile = eventText.substring(0, eventText.indexOf("("));
				final String errorLocation = eventText.substring(eventText.indexOf("(") + 1, eventText.indexOf(")"));
				final String[] rowAndColumn = errorLocation.split(",");

				final VirtualFile errorVirtualFile = VirtualFileUtil.findFileByLocalPath(errorFile);
				toRecompile.add(errorVirtualFile);
				context.addMessage(CompilerMessageCategory.ERROR, eventText, errorVirtualFile.getUrl(), Integer.valueOf(rowAndColumn[0]),
						Integer.valueOf(rowAndColumn[1]));
			}
		}

		public Set<VirtualFile> getFilesToRecompile()
		{
			return toRecompile;
		}
	}
}
