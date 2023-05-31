package org.fandev.java.runner;

import consulo.application.AllIcons;
import consulo.configurable.ConfigurationException;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.ui.ex.awt.ColoredListCellRenderer;
import consulo.execution.ui.awt.RawCommandLineEditor;
import consulo.module.Module;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * Date: Sep 5, 2009
 * Time: 11:40:21 PM
 *
 * @author Dror Bereznitsky
 */
public abstract class FanRunConfigurationEditor extends SettingsEditor<FanRunConfiguration>
{
	protected JPanel myMainPanel;
	protected JPanel modulesPanel;
	protected RawCommandLineEditor executionParametersField;
	protected DefaultComboBoxModel myModulesModel;
	protected JComboBox myModulesBox;

	public FanRunConfigurationEditor()
	{
		myMainPanel = new JPanel(new GridLayout(4, 1));

		addExectuionParamsSection(myMainPanel);
		addExecutablePanel(myMainPanel);
		addModulePanel(myMainPanel);
	}

	protected abstract void addExecutablePanel(final JPanel mainPanel);

	protected void addModulePanel(final JPanel mainPanel)
	{
		// Modules
		modulesPanel = new JPanel(new GridLayout(2, 1));
		myModulesBox = new JComboBox();
		myModulesModel = new DefaultComboBoxModel();
		modulesPanel.add(new JLabel("Choose classpath and sdk from module"));
		modulesPanel.add(myModulesBox);
		mainPanel.add(modulesPanel, BorderLayout.SOUTH);
	}

	protected void addExectuionParamsSection(final JPanel mainPanel)
	{
		final JLabel executionParametersLabel = new JLabel("Execution Parameters:");
		executionParametersField = new RawCommandLineEditor();
		executionParametersField.attachLabel(executionParametersLabel);
		executionParametersField.setDialogCaption("Execution Parameters");
		myMainPanel.add(executionParametersLabel, BorderLayout.CENTER);
		myMainPanel.add(executionParametersField, BorderLayout.CENTER);
	}

	protected void resetEditorFrom(final FanRunConfiguration configuration)
	{
		executionParametersField.setText(configuration.getExecutionParameters());
		myModulesModel.removeAllElements();
		for(final Module module : configuration.getValidModules())
		{
			myModulesModel.addElement(module);
		}
	}

	protected void applyEditorTo(final FanRunConfiguration configuration) throws ConfigurationException
	{
		configuration.setExecutionParameters(executionParametersField.getText());
		configuration.setModule((Module) myModulesBox.getSelectedItem());
	}

	@Nonnull
	protected JComponent createEditor()
	{
		myModulesBox.setModel(myModulesModel);

		myModulesBox.setRenderer(new ColoredListCellRenderer()
		{
			@Override
			protected void customizeCellRenderer(@Nonnull JList jList, Object value, int i, boolean b, boolean b1)
			{
				final Module module = (Module) value;
				if(module != null)
				{
					setIcon(AllIcons.Nodes.Module);
					append(module.getName());
				}
			}
		});

		return myMainPanel;
	}

	protected void disposeEditor()
	{

	}

	private void createUIComponents()
	{
		// TODO: place custom component creation code here
	}
}
