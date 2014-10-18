package org.sf.easyexplore.actions;

import org.eclipse.jface.action.IAction;
import org.sf.easyexplore.EasyExplorePlugin;

public class EasyExploreAction extends EasyBaseAction {

	public void runAction(IAction action) {
		try {
			String target = EasyExplorePlugin.getDefault().getTarget();
			run(action, target);
		} catch (Throwable e) {
			EasyExplorePlugin.log(e);
		}
	}
}
