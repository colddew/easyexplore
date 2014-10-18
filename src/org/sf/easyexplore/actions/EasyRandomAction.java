package org.sf.easyexplore.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
import org.sf.easyexplore.EasyExplorePlugin;

/*
 * author: Celinio Fernandes
 * Class to get the user defined command (set in the preferences) and then execute it
 */
public class EasyRandomAction extends EasyBaseAction {

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void runAction(IAction action) {
		try {
			String target = EasyExplorePlugin.getDefault().getRandom();
			run(action, target);
		} catch (Throwable e) {
			EasyExplorePlugin.log(e);
		}
	}
}
