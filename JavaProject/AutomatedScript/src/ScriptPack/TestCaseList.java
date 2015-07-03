package ScriptPack;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author syuniyar
 *07/05/15 syuniyar 		add comments
 *01/06/15 syuniyar			add comments
 *26/06/15 syuniyar			modify class association
 */

public class TestCaseList {
	/** object TestCaseList consists of object TestCase
	 *  tclpassed : true if all the pass status of all test cases are PAssed and stop the execution
	 *  statusListOt : dummy to store the status of test cases after execution
	 * 
	 */
	
	private static boolean tclpassed;
	private static ArrayList<String> statusListOt;
	private ArrayList<TestCase>TCList;
	
	private static Logger theLogger = Logger.getLogger("File1");
	
	public TestCaseList(){
		
	}
	public TestCaseList(ArrayList<TestCase>TCList){
		this.TCList = TCList ;
	}
	
	/**verify the passed status of test cases
	 * @param TCList : list to be verified
	 * @return tclpassed, will be false if there is at least 1 TC fails. Otherwise, return true
	 */
	public boolean  verifyList (){
		
		ArrayList<String> statusList = new ArrayList<String>();
		
		for (TestCase e : TCList){
			statusList.add(e.getIsPassed());
		}
	
		if (statusList.contains("Failed") == true) {
			tclpassed = false;
			theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " NOK");
		}else {
			tclpassed = true;	
			theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		}
		
		statusListOt = statusList;
		
		//theLogger.info("passed " + Thread.currentThread().getStackTrace()[1].getMethodName().toString());
		return tclpassed;
	}
	
	/**
	 * @return the size of passed status list 
	 */
	public int getTCPassed() {

		ArrayList<String> passedList = new ArrayList<String>();

		for (int i=0; i < statusListOt.size(); i++) {
			if (statusListOt.get(i).equals("Passed")){
				passedList.add(statusListOt.get(i));
			}		
		}
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		return passedList.size();	
	}
	
	/**
	 * @return size of the failed status list
	 */
	public int getTCFailed() {
		ArrayList<String> failedList = new ArrayList<String>();
		
			for (int i=0; i < statusListOt.size(); i++) {
				if (statusListOt.get(i).equals("Failed")){
					failedList.add(statusListOt.get(i));
				}	
			}
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		return failedList.size();
		}
	
	/**
	 * @return size of the crashed status list
	 */
	public int getTCCrashed() {
		ArrayList<String> crashedList = new ArrayList<String>();
		
			for (int i=0; i < statusListOt.size(); i++) {
				if (statusListOt.get(i).equals("Crashed")){
					crashedList.add(statusListOt.get(i));
				}	
			}
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		return crashedList.size();
		}
	
	public static void main(String[] args) {
		//System.err.println("statusListOt" + statusListOt);

	}

}

