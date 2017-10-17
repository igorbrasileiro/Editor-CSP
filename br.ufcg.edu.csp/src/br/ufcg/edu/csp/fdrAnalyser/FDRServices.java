package br.ufcg.edu.csp.fdrAnalyser;

import java.util.ArrayList;

import uk.ac.ox.cs.fdr.Assertion;
import uk.ac.ox.cs.fdr.Session;

public class FDRServices {

	private Session session;

	public FDRServices(String fileName) {
		session = new Session();
		session.loadFile(fileName);
	}
	
	/*
	 * Assertion assertTeste = session.parseAssertion("UpDownP [T= OPEN").result();
		assertTeste.execute(null);
		System.out.println(assertTeste.passed());
	 */
	/**
	 * This method return a list with assertions strings from process given. 
	 * First string is a Deadlock assertion. Second string is a Divergence assertion.
	 * @param processName - the name of process that will be checked
	 * @return ArrayList<String> 
	 */
	public ArrayList<String> checkProcessAssertions(String processName) {
		ArrayList<String> result = new ArrayList<>();
		
		// criar os 3 métodos
		result.add(checkDeadlockFree(processName));
		result.add(checkDeterministic(processName));
		
		
		return result;
	}
	// necessários deadlock, livelock e deterministic 
	//TODO:  livelock
	
	private String checkDeadlockFree(String processName) {
		// :[deadlock free [FD]]
		String assertString = processName+ " " + ":[deadlock free [FD]]";
		
		Assertion deadlockAssert = getAssertion(assertString);
		
		String result = "Deadlock: " + (deadlockAssert.passed() ? "Passed" : "Failed");
		
		return result;
	}
	
	private String checkDivergenceFree(String processName) {
		// :[divergence free [FD]]
		String assertString = processName+ " " + ":[divergence free [FD]]";
		
		Assertion divergenceAssert = getAssertion(assertString);
		
		String result = "Divergence " + (divergenceAssert.passed() ? "Passed" : "Failed");
		
		return result;
	}
	
	private String checkDeterministic(String processName) {
		//assert P :[deterministic]
		String assertString = processName + " " + ":[deterministic [FD]]";
		
		Assertion deterministicAssert = getAssertion(assertString);
		
		String result = "Deterministic: " + (deterministicAssert.passed() ? "Passed" : "Failed");
		
		return result;
	}
	
	private Assertion getAssertion(String assertion) {
		Assertion newAssertion = session.parseAssertion(assertion).result();
		newAssertion.execute(null);
		
		return newAssertion;
	}
}