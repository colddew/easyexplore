package org.sf.easyexplore;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.sf.easyexplore.preferences.AddCommandPreferencePage;
import org.sf.easyexplore.preferences.EasyExplorePreferencePage;

/**
 * Added support for a comamnd prompt and updated the default for openining Explorer in right folder.
 * Thanks to  Chris Gittings and anonymous on page : http://eclipse-plugins.info/eclipse/plugin_comments.jsp?id=192
 */
public class EasyExplorePlugin extends AbstractUIPlugin {
	// The shared instance.
	private static EasyExplorePlugin plugin;

	private ResourceBundle resourceBundle;

	public EasyExplorePlugin() {
		plugin = this;
		try {
			resourceBundle = ResourceBundle
					.getBundle("org.sf.easyexplore.EasyExplorePluginResources");
		} catch (MissingResourceException x) {
			// throw new NullPointerException("unnable to load resources
			// org.sf.easyexplore.EasyExplorePluginResources");
		}
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static EasyExplorePlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = EasyExplorePlugin.getDefault()
				.getResourceBundle();
		String res = null;
		try {
			res = bundle.getString(key);
		} catch (MissingResourceException e) {
			res = key;
		}
		return res;
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	static public void log(Object msg) {
		ILog log = EasyExplorePlugin.getDefault().getLog();
		Status status = new Status(IStatus.INFO, EasyExplorePlugin.getDefault()
				.getDescriptor().getUniqueIdentifier(), IStatus.ERROR, msg
				+ "\n", null);
		log.log(status);
	}

	static public void log(Throwable ex) {
		ILog log = EasyExplorePlugin.getDefault().getLog();
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		String msg = stringWriter.getBuffer().toString();
		Status status = new Status(IStatus.ERROR, EasyExplorePlugin
				.getDefault().getDescriptor().getUniqueIdentifier(),
				IStatus.ERROR, msg, null);
		log.log(status);
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#initializeDefaultPreferences(org.eclipse.jface.preference.IPreferenceStore)
	 * @author Celinio Fernandes : method to set up the default values for the preferences for the plug-in 
	 *  
	 */
	protected void initializeDefaultPreferences(IPreferenceStore store) {
		String osName = System.getProperty("os.name");

		String defaultTarget = "shell_open_command {0}";
		if (osName.indexOf("Windows") != -1) {
			defaultTarget = "explorer.exe /n,/e,{0}";
		} else if (osName.indexOf("Mac") != -1) {
			defaultTarget = "open {0}";
		}
		store.setDefault(EasyExplorePreferencePage.P_TARGET, defaultTarget);

		String defaultCommand = "shell_open_command {0}";
		if (osName.indexOf("Windows") != -1) {
			defaultCommand = "cmd /C start cmd /K cd {0}";
		} else if (osName.indexOf("Mac") != -1) {
			defaultCommand = "open {0}";
		}
		store.setDefault(EasyExplorePreferencePage.P_COMMAND, defaultCommand);
		
		String defaultRandom = "Type here your command";
		store.setDefault(AddCommandPreferencePage.P_RANDOM, defaultRandom);		
	}

	/**
	 * Return the target explorer program configured in
	 * EasyExplorePreferencePage.
	 * 
	 * @return String
	 */
	public String getTarget() {
		return getPreferenceStore().getString(EasyExplorePreferencePage.P_TARGET);
	}

	/**
	 * Return the target command program configured in
	 * EasyExplorePreferencePage.
	 * 
	 * @return String
	 */
	public String getCommand() {
		return getPreferenceStore().getString(EasyExplorePreferencePage.P_COMMAND);
	}
	
	/**
	 * @author: Celinio Fernandes
	 * Return the target random program configured in
	 * EasyExplorePreferencePage.
	 * 
	 * @return String
	 */
	public String getRandom() {
		return getPreferenceStore().getString(AddCommandPreferencePage.P_RANDOM);
	}

	/**
	 * Tells whether this platform is currently supported. The implementation of
	 * this method must be in sync with the implementation of <a
	 * href="#initializeDefaultPreferences(IPreferenceStore)">initializeDefaultPreferences(IPreferenceStore)</a>.
	 */
	public boolean isSupported() {
		String osName = System.getProperty("os.name").toLowerCase();
		return ((osName.indexOf("windows") != -1) || (osName.indexOf("mac") != -1));
	}
}
