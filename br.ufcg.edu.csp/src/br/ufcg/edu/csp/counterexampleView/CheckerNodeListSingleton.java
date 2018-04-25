package br.ufcg.edu.csp.counterexampleView;

import java.util.ArrayList;

import org.eclipse.ui.PlatformUI;

public class CheckerNodeListSingleton {

	private ArrayList<CheckerNodeDecorator> list;
	private static CheckerNodeListSingleton instance;
	
	public static CheckerNodeListSingleton getInstance() {
		if(instance == null) {
			instance = new CheckerNodeListSingleton();
		}
		
		return instance;
	}
	
	private CheckerNodeListSingleton() {
		list = new ArrayList<>();
	}
	
	public void updateList(CheckerNodeDecorator node) {
		node.setNodeAsCounterexampleNode();
		if(list.contains(node)) {
			int index = list.indexOf(node);
			CheckerNodeDecorator existentNode = list.get(index);
			existentNode.setCheckCondition(node.getCheckCondition());
		} else if(!list.contains(node)) {
			list.add(node);
		}
		updateCounterexampleListView();
	}
	
	private void updateCounterexampleListView() {
		/* CODIGO UTIL
		 * for(IWorkbenchWindow workbench : PlatformUI.getWorkbench().getWorkbenchWindows()) {
				for(IWorkbenchPage workbenchPage : workbench.getPages()) {
					workbenchPage.findView(); //nome da viewpart
				}
			}
		 */
		CounterexampleListView clv = (CounterexampleListView) PlatformUI.getWorkbench().
				getActiveWorkbenchWindow().getActivePage().
					findView("br.ufcg.edu.csp.CounterexampleListView");
		
		if(clv != null) {
			clv.updateContent(getListContent());
		}
	}
	
	public Object[] getListContent() {
		if(list.size() == 0) {
			return null;
		} else {
			return list.toArray();
		}
	}

	public boolean containsNode(CheckerNodeDecorator node) {
		return list.contains(node);
	}
 }
