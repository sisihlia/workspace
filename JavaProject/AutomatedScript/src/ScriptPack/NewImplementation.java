package ScriptPack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;
import freemarker.template.TemplateException;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;


	/**
	 * @author syuniyar
	 *07/05/15 syuniyar 		add comments
	 *11/05/15 syuniyar			delete unrelated code
	 *18/05/15 syuniyar			modify arguments input
	 *28/05/15 syuniyar			add help menu
	 *01/06/15 syuniyar			add comments
	 *26/06/15 syuniyar			modify class association
	 
	 */

public class NewImplementation extends Thread {
		
	private static Logger theLogger = Logger.getLogger("File1");
	private final String versionReturn = " autoScript version 123";
	
	// initialization of each class	
	private static Execution ex = new Execution ();
	private static Section s = new Section();
	private static Chart chr= new Chart();
	private static PropertiesFile ep = new PropertiesFile();
	private static NewImplementation im = new NewImplementation();
	private static MyFormatter formatter = new MyFormatter();	  
	
	
	public void writeToFile(String loc) throws SecurityException, IOException{
		FileHandler fh =  new FileHandler(loc);
		theLogger.addHandler(fh);
		
		fh.setFormatter(formatter); 
	}
		  
//		 @Parameter(description = "value")
//		 private String value;
		 
	@Parameter(names = {"-help" }, description = "print this message")
	private boolean help =false;
		 
	@Parameter(names={"-config"},description=" path to configuration file , mandatory", required = true)
	public String config; 
		 
	@Parameter(names={"-section"},description=" section number to run, mandatory", required = true)
	public String section;

	@Parameter(names={"-itMax"},description="iteration number to run, mandatory", required = true )
	public String itMax; 

	@Parameter(names = {"-chart"}, description = "generated output chart name, mandatory", required = true)
	private String chart;

	@Parameter(names={"-bin"},description="path to ET bin dir, mandatory", required = true )
	public String bin; 
		
	@Parameter(names={"-log"},description="generated log file name, optional")
	public boolean log = false; 
		
	@Parameter(names={"-version"},description="version number")
	public boolean version = false ; 
		
	public  static void main(String[] args) throws IOException, ConfigurationException, TemplateException {

		JCommander commands= new JCommander(im);
		commands.setProgramName("java -jar autoScript.jar");
		
	// will not validate the argument number when it is only 1, execute version and help 
		if (args.length == 1) {
			commands.parseWithoutValidation(args);
			try {
				 
				 //commands.parseWithoutValidation(args);
				 if (im.help) {
		            commands.usage();
		            System.exit(0);
				 }
				 if (im.version) {
		        	System.out.println(im.versionReturn);
			        System.exit(0);
			     }	
				
				
			}catch (Exception e) {
				System.out.println(e.toString()); 
			}
		}
	
	// arguments more than 1 should be validated	
		try {
			commands.parse(args);
			 if(im.log){
				 System.out.println("log is generated in current folder");
				 im.writeToFile("Implementation.log");
			 } 
			ArrayList<TestCase> TClist = s.convertStringToTestCase(s.findSection(im.config, im.section.split(" ")));
			TestCaseList tc = new TestCaseList(TClist);
	        ex.executeTestCaseList(TClist, Integer.parseInt(im.itMax), im.bin);
	        ex.getTCAttribute(TClist);
	        ep.setConfigProperties(tc);
			chr.generateHistory();
	        chr.generateChartAndTableTemplate(ex.getNameList(), ex.getPassedList().toString(), ex.getItList().toString(), im.chart);
	      
		 } catch (Exception e) {
			  System.out.println("Please fill mandatory field");
	          System.out.println(e.toString()); 
		 }
	}
}

