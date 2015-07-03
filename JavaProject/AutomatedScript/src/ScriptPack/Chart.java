package ScriptPack;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.net.URL;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import freemarker.cache.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;


/**
 * *@author syuniyar
 * 07/05/15 syuniyar 		add comments 
 * 29/05/15 syuniyar		add TCCrashed component
 * 01/06/15 syuniyar		add comments
 * 26/06/15 syuniyar		modify class association
 *
 */
public class Chart extends ClassTemplateLoader {
	
	String[] tcPassed;
	String[] tcFailed;
	String[] tcCrashed;
	String[] executionTime;
	String str = "";

	private ClassLoader classLoader;
	private static TestCaseList tc = new TestCaseList();
	private static Logger theLogger = Logger.getLogger("File1");
	
	public Chart (){
		
	}
	
	/**extend the constructor of class ClassTemplatLoader
	 * @param clazz: get the class to load the template
	 * @param string : template dir
	 */
	public Chart(Class clazz, String string) {
   	 
        super(clazz, string);
        classLoader = clazz.getClassLoader();
        theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
	}
	@Override
	protected URL getURL(String name) {
        // this now loads the template file from the jar
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
        return classLoader.getResource(name);
	} 
	
	/**generate the bar chart from properties file which stores the trace as 
	 * the number of TCpassed, TCfailed, and TCcrashed from each execution of a list
	 * @throws IOException
	 * @throws TemplateException
	 * @throws ConfigurationException
	 */
	public void generateHistory () throws IOException, TemplateException, ConfigurationException  {
		
		AbstractConfiguration config = new PropertiesConfiguration("config.properties");  
		tcPassed = config.getStringArray("TCpassed");
		tcFailed= config.getStringArray("TCfailed");
		tcCrashed = config.getStringArray("TCcrashed");
		executionTime = config.getStringArray("Execution_time");
		
		System.out.println(Arrays.toString(tcPassed));
		System.out.println(Arrays.toString(tcFailed));
		System.out.println(Arrays.toString(tcCrashed));
		System.out.println(Arrays.toString(executionTime));
		
		theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
	}
	
	/**generate the pie chart of TCpassed and TC failed number per execution and table presents the
	 * TC name, TC pass status, TC iteration number per execution from html template 'exampleReport.html' in classpath
	 * @param passed
	 * @param failed
	 * @param nameList
	 * @param passedList
	 * @param itList
	 * @param chartLoc
	 */
	public void generateChartAndTableTemplate(String nameList, String passedList, String itList, String chartLoc){
		
        try {
        	 Version version = new Version(2, 3, 22);
   	      	 Configuration config = new Configuration(version);   	
 			 ClassTemplateLoader ctl = new Chart(getClass(), "resources");
 		     TemplateLoader[] loaders = new TemplateLoader[] { ctl };
 		     MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
 		     config.setTemplateLoader(mtl);
 		     Template template = config.getTemplate("resources/exampleReport.html"); 
 			
          	
        	Map<String, Object> data = new HashMap<String, Object>();
            data.put("failedVal", tc.getTCFailed());
            data.put("PassedVal", tc.getTCPassed());
            data.put("CrashedVal", tc.getTCCrashed());
            data.put("it", itList);
            data.put("nameList", nameList);
            data.put("passedList", passedList);
            data.put("historyPassedResult",Arrays.toString(tcPassed));
            data.put("historyFailedResult",Arrays.toString(tcFailed));
            data.put("historyCrashedResult",Arrays.toString(tcCrashed));
            data.put("historyExecutionTrack", Arrays.toString(executionTime));
          
            
            Writer out = new OutputStreamWriter(System.out);
            template.process(data, out);
            out.flush();
 
            // File output
            Writer file = new FileWriter (new File(chartLoc));
            template.process(data, file);
            file.flush();
            file.close();
             
            theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
            
        } catch (IOException e) {
            e.printStackTrace();
            theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " NOK");
        } catch (TemplateException e) {
            e.printStackTrace();
            theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " NOK");
        }
	}

    public static void main(String[] args) throws IOException, TemplateException, ConfigurationException {
      
    }	
}