package ScriptPack;

import java.util.logging.Logger;

/**
 * @author syuniyar
 *07/05/15 syuniyar 		add comments 
 *01/06/15 syuniyar			add comments
 *26/06/15 syuniyar			modify class association
 */
public class TestCase {
	private String name;
	private String isPassed = "Failed";
	private int iterationNumber;
	
	 private static Logger theLogger = Logger.getLogger("File1");
	/**set the name of object testcase
	 * @param name
	 */
	public TestCase(String name){
		this.name = name;
	}
	
	/**
	 * @return the name of object testcase
	 */
	public String getName(){
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		return name;
	}
	
	/**
	 * @return the pass status of object testcase
	 */
	public String getIsPassed(){
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		
		return isPassed;
	}
	
	/**set the pass status to be Passed when testcase passed
	 * 
	 */
	public void setIsPassed(){
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		isPassed = "Passed";
	}
	
	/**set the pass status to be Failed when testcase failed
	 * 
	 */
	public void setIsNotPassed(){
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		isPassed = "Failed";
	}
	
	/**set the pass status to be Crashed when testcase crashed
	 * 
	 */
	public void setCrached(){
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		isPassed = "Crashed";
	}
	
	/**
	 * @return the iteration number of testcase passed, start from 0
	 */
	public int getIterationNumber(){
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		return iterationNumber;
	}
	
	/**
	 * set the iteration number plus 1 every time testcase is run
	 */
	public void settIterationNumber(){
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		iterationNumber +=1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
