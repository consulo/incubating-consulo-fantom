package org.fandev.runner;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.annotation.Nonnull;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.ui.ListCellRendererWrapper;
import com.intellij.ui.RawCommandLineEditor;

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

		myModulesBox.setRenderer(new ListCellRendererWrapper<Object>()
		{
			@Override
			public void customize(JList list, Object value, int index, boolean selected, boolean hasFocus)
			{
				final Module module = (Module) value;
				if(module != null)
				{
					setIcon(AllIcons.Nodes.Module);
					setText(module.getName());
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
