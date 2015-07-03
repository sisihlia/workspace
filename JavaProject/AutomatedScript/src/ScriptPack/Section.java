package ScriptPack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.ini4j.Ini;


/**
 * @author syuniyar
 *07/05/15 syuniyar 		add comments 
 *19/05/15 syuniyar			log contents
 *26/06/15 syuniyar			modify class association
 */
public class Section {
	
	 private Ini.Section root;
	 private ArrayList<String> StringList = new ArrayList<String>();
	 private ArrayList<TestCase> TCList = new ArrayList<TestCase>();
	 String sectionSearched;
	 
	 String filenameSearched;
	 
	 private static Logger theLogger = Logger.getLogger("File1");
	 /**
	 * define two constructors 
	 */
	public Section (){
		 
	 }
	 
	 /**
	  * construct an object Section with attibutes : 
	 * @param filenameSearched : file where to find the sections
	 * @param sectionSearched : sections to be executed
	 */
	public Section (String filenameSearched, String sectionSearched) {
		 this.sectionSearched = sectionSearched;
		 this.filenameSearched = filenameSearched;
	 }
	 
	 /**
	  * 
	  * find the sections and store each value inside as a string in an arraylist then return the list
	  * 
	 */
	public ArrayList<String> findSection(String filename, String... wanted) throws IOException, IOException {
		
			//theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString());
			
			Ini myIni = new Ini(new File (filename));
			for (String d : wanted) {
				root = myIni.get(d);
			
					for (String name : root.keySet()){
						StringList.add(root.get(name));
				
					}
			  }
			  theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
			  return StringList;
	  }		
	  
	  /**convert the list of string (testcase) to the list of object (testcase) and return the list
	 * @param StringList
	 * @return
	 */
	public ArrayList<TestCase> convertStringToTestCase(ArrayList<String>StringList){
		
		  for ( int i = 0; i < StringList.size(); i++) {
			  String name = StringList.get(i) ;
			  TestCase tc = new TestCase (name);
			  TCList.add(tc);
		  }
		  theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
		  return TCList;
	  }
	  
	
	public static void main(String[] args) throws IOException {
		String filename = "C:\\Users\\syuniyar\\.EasyTest\\4\\ccl\\config\\test2.cfg";
		Section s = new Section();
		
		s.convertStringToTestCase(s.findSection(filename, "12345"));
	
	}

}
