package org.sf.easyexplore.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.sf.easyexplore.EasyExplorePlugin;


/**
 * @author Celinio Fernandes 
 * New command class  
 * 
 */

public class AddCommandPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {
	
	// CF: the identifiers for the preferences	
	public static final String P_RANDOM = "org.sf.easyexplore.randomPreference";
	public static final String P_RANDOM_TITLE = "org.sf.easyexplore.randomPreference";
	
	public AddCommandPreferencePage() {
		super(GRID);
		setPreferenceStore(EasyExplorePlugin.getDefault().getPreferenceStore());		
		setDescription("Define your own command here");
	}

	/**
	 * CF: Method to add the field editors to the page 
	 * 
	 */
	public void createFieldEditors() {
		addField(new StringFieldEditor(P_RANDOM_TITLE, "&Title:",	getFieldEditorParent()));
		addField(new StringFieldEditor(P_RANDOM,  "&Random:",	getFieldEditorParent()));
	}

	public void init(IWorkbench workbench) {
	}
}