package ScriptPack;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author syuniyar
 *07/05/15 syuniyar 		add comments 
 *29/05/15 syuniyar			add TCCrashed component
 *26/06/15 syuniyar			modify class association
 */
public class PropertiesFile {
	
	/**generate the file config.properties for the 1st execution then store the result inside it.
	 * load the file config.properties for the next execution and add the result of current execution
	 * 
	 * @param tcPassed
	 * @param tcFailed
	 * @throws FileNotFoundException
	 */
	
	private static Logger theLogger = Logger.getLogger("File1");
	
	public void setConfigProperties(TestCaseList tc) throws FileNotFoundException{
		
		 try (
				 OutputStream output = new FileOutputStream("config.properties", true)) {
			 
	            Properties prop = new Properties();
	            
	            // set the properties value
	            String timeStamp = new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\"").format(Calendar.getInstance().getTime());
	           
	            prop.setProperty("TCpassed", ""+ tc.getTCPassed());
			    prop.setProperty("TCfailed", ""+ tc.getTCFailed());
			    prop.setProperty("TCcrashed", ""+ tc.getTCCrashed());
			    prop.setProperty("Execution_time", ""+ timeStamp);
			    
	            // save properties to project root folder.
	            prop.store(output, null);
	            
	            theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " OK");
	            
	        } catch (IOException exception) {
	            exception.printStackTrace();
	            theLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toString() + " NOK");
	        } 
		 
		 
	}
	
		public static void main(String[] args) throws FileNotFoundException {
			String timeStamp = new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(Calendar.getInstance().getTime());
	        System.out.println(timeStamp);
		}
}

