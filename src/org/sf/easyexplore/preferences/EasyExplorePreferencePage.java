package org.sf.easyexplore.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.sf.easyexplore.EasyExplorePlugin;


/**
 * @author Celinio Fernandes 
 * Preferences class for the commands executed by the Easy plug-in
 * 
 */

public class EasyExplorePreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {
	
	// CF: the identifiers for the preferences
	public static final String P_TARGET = "org.sf.easyexplore.targetPreference";
	public static final String P_COMMAND = "org.sf.easyexplore.commandPreference";
	
	/*
	 * CF: set a preference store for the preference page 
	 */
	public EasyExplorePreferencePage() {
		super(FieldEditorPreferencePage.GRID);
		setPreferenceStore(EasyExplorePlugin.getDefault().getPreferenceStore());		
		setDescription("Set the operating system commands to open an explorer and a command shell.");
	}

	/**
	 * CF: Method to add the field editors to the page 
	 * 
	 */
	public void createFieldEditors() {
		addField(new StringFieldEditor(P_TARGET,  "&Explorer:",	getFieldEditorParent()));
		addField(new StringFieldEditor(P_COMMAND, "&Command:",	getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {
	}
}