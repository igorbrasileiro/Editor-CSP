package br.ufcg.edu.csp.counterexampleView;

import org.antlr.v4.runtime.tree.ParseTree;

import br.ufcg.edu.csp.parser.CspParser;
import br.ufcg.edu.csp.utils.INodeDecorator;

public class CheckerNodeDecorator implements INodeDecorator {
	
	private boolean isCounterexampleNode;
	
	private ParseTree node;
	private boolean checkCondition;
	private String[] counterexamples;
	private String assertionText;
	

	public CheckerNodeDecorator(ParseTree node) {
		this.node = node;
		this.checkCondition = false;
		this.isCounterexampleNode = false;
		this.assertionText = "";
	}
	
	public ParseTree getNode() {
		return this.node;
	}
	
	public boolean getCheckCondition() {
		return this.checkCondition;
	}
	
	public void setCheckCondition(boolean value) {
		this.checkCondition = value;
	}

	public String[] getCounterexamples() {
		return this.counterexamples;
	}
	
	public void setCounterexamples(String[] nodeCounterexamples) {
		this.counterexamples = nodeCounterexamples;
	}

	@Override
	public boolean equals (Object o) {
		return this.getAssertionText().equals(((CheckerNodeDecorator)o).getAssertionText());
	}

	@Override
	public void setNode(ParseTree node) {
		this.node = node;
	}

	public String getAssertionText() {
		return assertionText;
	}

	public void setAssertionText(String assertionText) {
		this.assertionText = assertionText;
	}
	
	public void setNodeAsCounterexampleNode() {
		this.isCounterexampleNode = true;
	}
	
	public boolean getIsCounterexampleNode() {
		return this.isCounterexampleNode;
	}
	
	public String getNodeName() { 
		return node.getChild(0).getText();
	}
	
	@Override
	public String toString() {
		String text;
		if(isCounterexampleNode) {
			text =  getAssertionText();
		} else if(node instanceof CspParser.AssertDefinitionContext) {
			text = node.getText();
		} else {
			text =  getNodeName();
		}
		
		return text;
	}
}
