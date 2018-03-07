package br.ufcg.edu.csp.fdrAnalyser;


import java.util.ArrayList;

import uk.ac.ox.cs.fdr.Assertion;

public class DeterministicChecker extends FDRServices implements FDRChecker {
	
	private static final String CHECKER_DECLARATION = " :[deadlock free [FD]]";

	public DeterministicChecker(String fileName) {
		super(fileName);
	}

	@Override
	public String checkProcess(String processName) {
		return checkDeterministic(processName);
	}
	
	@Override
	public String[] getCounterExamples(String processName) {
		String assertString = processName + CHECKER_DECLARATION;

		Assertion assertion = getAssertion(assertString);
		
		ArrayList<String> listCounterExample = new ArrayList<>();
		
		describeCounterexample(assertion, listCounterExample);
		
		return listCounterExample.toArray(new String[1]);
	}
	
	public String checkDeterministic(String processName) {
		//assert P :[deterministic]
		String assertString = processName + " " + ":[deterministic [FD]]";
		
		Assertion assertion = getAssertion(assertString);
		String result = null;
		if(assertion != null ) {
			result = "Deterministic: " + (assertion.passed() ? "Passed" : "Failed" + " add falha");
		}
		
		return result;
	}
}
