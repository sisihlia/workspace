package ScriptPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * @author syuniyar
 *07/05/15 syuniyar 		add comments 
 *29/05/15 syuniyar			add TCCrashed component
 *01/06/15 syuniyar			add comments
 *26/06/15 syuniyar			modify class association
 */
public class Execution {
	private String section;
	String config;
	int itMax;
	String runTCdir;
	
	
	private List<String> nameList = new ArrayList<String>();
	private List<String> statusList = new ArrayList<String>();
	private List<String> itList = new ArrayList<String>();
	
	private static ArrayList<TestCase>TC = new ArrayList<TestCase>(); //CHECK
	
	//private static Logger theLogger = Logger.getLogger(Execution.class.getName());
	 private static Logger theLogger = Logger.getLogger("File1");
	public Execution () {
		

	}
	
	/**execute runTC from indicated working dir and test case name in configuration file.
	 * Once it finds 'exit value', it increments the iteration number
	 * Once it finds 'exit value == 0', it sets the status of test case to be true
	 * @param TC
	 * @param workingDirectory : dir where runTC.exe is
	 * @throws IOException
	 */
	
	public void executeTestCase(TestCase TC, String workingDirectory ) {
		
		//String workingDirectory = "C:\\EasyTest\\4\\bin\\runTC.exe ";
		
		String argument1 = workingDirectory +" "+ TC.getName();
		try {
			Process p = Runtime.getRuntime().exec(argument1);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String s = null;
			boolean notFound = false;
			while ((s = stdInput.readLine()) != null) {
				 	//System.out.println(s);
				 	theLogger.info(s);		
			
				 	//System.out.println(s);
				 	if (s.contains("doesn't exist"))  notFound = true;
			}
			
			theLogger.info( "NotFound " + notFound);
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long exitValue = p.exitValue();
			
		
			theLogger.info("exit value "+ exitValue);
			
			TC.settIterationNumber();
			
				
			
			if (exitValue == 0){
		    	TC.setIsPassed();
		    	theLogger.info("TC is passed");
			} 
			if (exitValue == -1073741819) {
				TC.setCrached();
			
			}
			if (exitValue == -1){
				if (notFound == true) TC.setCrached();
				else TC.setIsNotPassed();
			}
			
			stdInput.close();
			p.getInputStream().close();
			p.getOutputStream().close();
			p.getErrorStream().close();
			theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		}catch (IOException e){
			 e.printStackTrace();
			 theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " NOK");
	
		}
		
	}
			
	/**
	 * this will call executeTestCase methode to execute each test case in given list until given iteration number 
	 * Once all the test cases are pass, it gets out from the loop.
	 * 
	 * @param TCList
	 * @param max
	 * @param workingDirectory
	 * @throws IOException
	 */
	public void executeTestCaseList (ArrayList<TestCase> TCList, int max, String workingDirectory) throws IOException{		
		TestCaseList tcl = new TestCaseList(TCList);
		
		for (int x = 1; x<=max; x++){
					theLogger.info("TC Execution starts for iteration " + x);
					for (int i=0 ; i<TCList.size(); i++) {
						theLogger.info("TC Execution for TC " + (i+1));
							if (TCList.get(i).getIsPassed() == "Passed") {
								continue;
							}else if (TCList.get(i).getIsPassed() == "Crashed"){
								continue;
							}else {
								executeTestCase(TCList.get(i), workingDirectory);
							}
					
					//System.out.println("Iteration number : "+ TCList.get(i).getIterationNumber());
					theLogger.info("Iteration number : "+ TCList.get(i).getIterationNumber());
					}
					
					theLogger.info("verify status ALL TC");
					if(tcl.verifyList() == true) {
						theLogger.info("All TC pass. Execution ends");
						break;
						
					}
					theLogger.info("TC Execution ends for iteration " + x);
			}	
		theLogger.info("Execution for all iteration ends");
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
	}
			
	public void getTCAttribute(ArrayList<TestCase> tcl) {
		
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " starts");
		for (int i=0; i<tcl.size();i++){
			
			String status ="" + tcl.get(i).getIsPassed();
			String itNum = "" + tcl.get(i).getIterationNumber();
			statusList.add("\"" + status + "\"");
			itList.add(itNum);
		}
	
		for (int i=0; i<tcl.size();i++){
			String name = tcl.get(i).getName();
			String[] items = name.split("\\s|/");
			nameList.add(items[1]);	
		}
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
	}
	
	/**get nameList with quote  added to be compatible with freemarker template
	 * @return
	 */
	public String getNameList(){
		String withQuote = "";
		
		for (int i =0; i<nameList.size(); i++){
			withQuote += "\"" + nameList.get(i) + "\"";
	        if (i != nameList.size() - 1) {
	        	withQuote += ", ";
	        }
		}	
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		return "[" + withQuote +"]";
	}

	/**
	 * @return the statusList
	 */
	public List<String> getPassedList(){
		
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		return statusList;
	}

	/**
	 * @return the iterationList
	 */
	public List<String> getItList(){
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		return itList;
	}

	public static void main(String[] args) throws IOException {
		Execution ex = new Execution();
		//ex.getSection();
		
	}

}

