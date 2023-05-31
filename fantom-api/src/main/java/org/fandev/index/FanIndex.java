package org.fandev.index;

import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ServiceAPI;
import consulo.annotation.component.ServiceImpl;
import consulo.application.ApplicationManager;
import consulo.content.base.BinariesOrderRootType;
import consulo.content.bundle.Sdk;
import consulo.content.library.Library;
import consulo.content.library.LibraryTable;
import consulo.fantom.module.extension.FanModuleExtension;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.language.psi.stub.StubIndex;
import consulo.language.util.ModuleUtilCore;
import consulo.logging.Logger;
import consulo.module.Module;
import consulo.module.ModuleManager;
import consulo.module.content.ModuleRootManager;
import consulo.module.content.layer.ModifiableRootModel;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.fandev.lang.fan.PodFileType;
import org.fandev.lang.fan.psi.FanFile;
import org.fandev.lang.fan.psi.api.statements.typeDefs.FanTypeDefinition;
import org.fandev.lang.fan.psi.stubs.index.FanShortClassNameIndex;
import org.fandev.utils.FanUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * @author Dror Bereznitsky
 * @date Mar 4, 2009 11:37:54 PM
 */
@Singleton
@ServiceAPI(value = ComponentScope.PROJECT, lazy = false)
@ServiceImpl
@Deprecated // TODO need rework
public class FanIndex
{
	private final Project project;
	private final PsiManager psiManager;
	private final Map<String, VirtualFile> typeToFile = new HashMap<String, VirtualFile>();
	private final Map<String, Set<String>> libNameToTypesSet = new HashMap<String, Set<String>>();
	private final Map<String, String> podNameToLibName = new HashMap<String, String>();
	private final Map<String, VirtualFile> podToBuildFile = new HashMap<String, VirtualFile>();

	private static final Logger logger = Logger.getInstance("org.fandev.index.FanIndex");

	@Inject
	public FanIndex(final Project project, PsiManager psiManager)
	{
		this.project = project;
		this.psiManager = psiManager;
	}

	public VirtualFile getVirtualFileByTypeName(@Nonnull final String typeName)
	{
		final Collection<FanTypeDefinition> typeDefs = getProjectTypes(typeName);
		if(typeDefs != null && typeDefs.size() > 0)
		{
			return typeDefs.iterator().next().getContainingFile().getVirtualFile();
		}
		return typeToFile.get(typeName);
	}

	public VirtualFile getVirtualFileByPodName(@Nonnull final String podName)
	{
		return podToBuildFile.get(podName);
	}

	@Nullable
	public FanFile getFanFileByTypeName(@Nonnull final String typeName)
	{
		final VirtualFile typeFile = getVirtualFileByTypeName(typeName);
		if(typeFile != null)
		{
			return (FanFile) psiManager.findFile(typeFile);
		}
		return null;
	}

	public Set<String> getLibraryTypeNames(@Nonnull final String libName)
	{
		if(libNameToTypesSet.containsKey(libName))
		{
			return libNameToTypesSet.get(libName);
		}
		else
		{
			return new HashSet<String>();
		}
	}

	public Set<String> getPodTypeNames(@Nonnull final String podName)
	{
		final String libName = podNameToLibName.get(podName);
		if(libName != null && libNameToTypesSet.containsKey(libName))
		{
			return libNameToTypesSet.get(libName);
		}
		else
		{
			return new HashSet<String>();
		}
	}

	public Set<String> getAllPodNames()
	{
		return podToBuildFile.keySet();
	}

	@Nullable
	public FanFile getFanFileByPodName(@Nonnull final String podName)
	{
		final VirtualFile typeFile = getVirtualFileByPodName(podName);
		if(typeFile != null)
		{
			return (FanFile) psiManager.findFile(typeFile);
		}
		return null;
	}

	public Set<FanTypeDefinition> getPodStartingWith(@Nonnull final String prefix)
	{
		final Set<FanTypeDefinition> matching = new HashSet<FanTypeDefinition>();

		for(final String podName : getAllPodNames())
		{
			final FanFile file = getFanFileByPodName(podName);
			for(final FanTypeDefinition def : file.getTypeDefinitions())
			{
				if(FanUtil.isFanBuildScript(def) && def.getName().startsWith(prefix))
				{
					matching.add(def);
				}
			}
		}
		return matching;
	}

	public Set<String> getAllTypeNames()
	{
		final Set<String> allTypes = new HashSet<String>();
		for(final Set<String> libTypes : libNameToTypesSet.values())
		{
			allTypes.addAll(libTypes);
		}
		return allTypes;
	}

	public Set<FanTypeDefinition> getAllTypesStartingWith(@Nonnull final String prefix)
	{
		final Set<FanTypeDefinition> matching = new HashSet<FanTypeDefinition>();
		for(final String name : getAllTypeNames())
		{
			if(name.startsWith(prefix))
			{
				final FanFile typeFile = getFanFileByTypeName(name);
				if(typeFile != null)
				{
					for(final FanTypeDefinition def : typeFile.getTypeDefinitions())
					{
						if(def.getName().startsWith(prefix))
						{
							matching.add(def);
						}
					}
				}
			}
		}
		return matching;
	}

	public Set<FanTypeDefinition> getPodTypesStartingWith(@Nonnull final String podName, @Nonnull final String prefix)
	{
		final Set<FanTypeDefinition> matching = new HashSet<FanTypeDefinition>();
		for(final String name : getPodTypeNames(podName))
		{
			if(name.startsWith(prefix))
			{
				final FanFile typeFile = getFanFileByTypeName(name);
				if(typeFile != null)
				{
					for(final FanTypeDefinition def : typeFile.getTypeDefinitions())
					{
						if(def.getName().startsWith(prefix))
						{
							matching.add(def);
						}
					}
				}
			}
		}
		return matching;
	}

	public Set<VirtualFile> getLibraryVirtualFiles(@Nonnull final String libName)
	{
		final Set<String> types = getLibraryTypeNames(libName);
		final Set<VirtualFile> files = new HashSet<VirtualFile>(types.size());
		for(final String type : types)
		{
			final VirtualFile file = getVirtualFileByTypeName(type);
			if(file != null)
			{
				files.add(file);
			}
		}
		return files;
	}

	public Set<PsiFile> getLibraryPsiFiles(@Nonnull final String libName)
	{
		return getPsiFiles(getLibraryVirtualFiles(libName));
	}

	public Set<VirtualFile> getAllVirtualFiles()
	{
		return new HashSet<VirtualFile>(typeToFile.values());
	}

	public Set<PsiFile> getAllPsiFiles()
	{
		return getPsiFiles(getAllVirtualFiles());
	}

	private Set<PsiFile> getPsiFiles(final Set<VirtualFile> files)
	{
		final Set<PsiFile> psiFiles = new HashSet<PsiFile>(files.size());
		for(final VirtualFile file : files)
		{
			final PsiFile psiFile = psiManager.findFile(file);
			if(psiFile != null)
			{
				psiFiles.add(psiFile);
			}
		}
		return psiFiles;
	}

	private FanTypeDefinition getProjectType(@Nonnull final String podName, @Nonnull final String typeName)
	{
		final Collection<FanTypeDefinition> types = getProjectTypes(typeName);
		if(types.size() > 0)
		{
			for(final FanTypeDefinition typeDef : types)
			{
				if(typeName.equals(typeDef.getName()) && podName.equals(typeDef.getPodName()))
				{
					return typeDef;
				}
			}
		}
		return null;
	}

	private Collection<FanTypeDefinition> getProjectTypes(@Nonnull final String typeName)
	{
		return StubIndex.getInstance().get(FanShortClassNameIndex.KEY, typeName, project, null);
	}

	public void projectOpened()
	{
		final Module[] modules = ModuleManager.getInstance(project).getModules();
		for(final Module module : modules)
		{
			FanModuleExtension extension = ModuleUtilCore.getExtension(module, FanModuleExtension.class);
			if(extension != null)
			{
				final Sdk sdk = extension.getSdk();
				if(sdk == null)
				{
					logger.warn("Module " + module.getName() + " has no valid Fantom sdk");
					continue;
				}
				FanUtil.setFanHome(sdk);

				// Scan for Pod libraries
				final ModifiableRootModel modifiableRootModel = ModuleRootManager.getInstance(module).getModifiableModel();
				final LibraryTable libraryTable = modifiableRootModel.getModuleLibraryTable();
				final Library[] libraries = libraryTable.getLibraries();
				for(final Library library : libraries)
				{
					indexLibrary(library);
				}
			}
		}
	}

	public void indexLibrary(final Library library)
	{
		logger.debug("Indexing library " + library.getName());
		final String podName = library.getName().indexOf(' ') > 0 ? library.getName().substring(0, library.getName().indexOf(' ')) : library.getName();
		ApplicationManager.getApplication().runReadAction(new Runnable()
		{
			public void run()
			{
				final VirtualFile[] files = library.getFiles(BinariesOrderRootType.getInstance());
				for(final VirtualFile libFile : files)
				{
					if(libFile.isDirectory())
					{
						// Nothing to do
					}
					else if(libFile.getFileType() == PodFileType.POD_FILE_TYPE)
					{
						try
						{

							final String libName = libFile.getName();
							if(!libNameToTypesSet.containsKey(libName))
							{
								libNameToTypesSet.put(libName, new HashSet<String>());
							}
							final Set<String> libTypes = libNameToTypesSet.get(libName);

							String podName;
							if(libName.indexOf(".pod") > 0)
							{
								podName = libName.substring(0, libName.indexOf(".pod"));
								podNameToLibName.put(podName, libName);
							}
							else
							{
								logger.warn("Library " + libName + " is not a pod");
								continue;
							}
							// Index Pod types
							//							final Pod pod = Pod.find(podName);
							//							if(pod != null)
							//							{
							//								final VirtualFile[] srcFiles = library.getFiles(OrderRootType.SOURCES);
							//								if(srcFiles.length > 0)
							//								{
							//									final VirtualFile srcRoot = srcFiles[0];
							//									final VirtualFile podBuildFile = srcRoot.findFileByRelativePath("build.fan"); // Assuming we have only one source lib
							//									if(podBuildFile != null)
							//									{
							//										podToBuildFile.put(podName, podBuildFile);
							//									}
							//
							//									final fan.sys.List podTypes = pod.types();
							//									for(int i = 0; i < podTypes.size(); i++)
							//									{
							//										final fan.sys.Type type = (fan.sys.Type) podTypes.get(i);
							//										final VirtualFile srcFilePath = srcRoot.findFileByRelativePath(String.format("%s%s%s.%s", FanSdkType.getFanSrcDir(),
							//												VirtualFileUtil.VFS_PATH_SEPARATOR, type.name(), FanFileType.DEFAULT_EXTENSION));
							//										libTypes.add(type.name());
							//										if(srcFilePath != null)
							//										{
							//											typeToFile.put(type.name(), srcFilePath);
							//										}
							//									}
							//								}
							//							}
						}
						catch(Exception e)
						{
							logger.warn("Could not index library " + libFile.getPath() + ":" + e.getMessage());
						}
					}
				}
			}
		});
	}

	public void projectClosed()
	{
		typeToFile.clear();
		libNameToTypesSet.clear();
		podNameToLibName.clear();
	}
}
