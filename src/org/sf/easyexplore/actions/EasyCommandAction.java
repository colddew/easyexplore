package org.sf.easyexplore.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
import org.sf.easyexplore.EasyExplorePlugin;

public class EasyCommandAction extends EasyBaseAction {

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void runAction(IAction action) {
		try {
			String target = EasyExplorePlugin.getDefault().getCommand();
			run(action, target);
		} catch (Throwable e) {
			EasyExplorePlugin.log(e);
		}
	}
}
