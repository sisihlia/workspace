
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 



import freemarker.cache.*;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class HelloWorld {
	     
		
		
		public void generateTemplate(){
			
			
	       
	        try {
	        	
	            //Load template from source folder
	        	//FileTemplateLoader ftl1 = new FileTemplateLoader(new File("/home/sisily/workspace_Git"));
	        	 Version version = new Version(2, 3, 22);
	   	      
	 			Configuration config = new Configuration(version);
	 			
	        	config.setDirectoryForTemplateLoading(new File("/home/sisily/workspace_Git"));
	        	
	        	
	        	Template template = config.getTemplate("helloWorldTemplate.html");
	           // TemplateLoader templ = new TemplateLoader(ftl1);
	           
	            //ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), "");
	           
	          //  TemplateLoader[] loaders = new TemplateLoader[] { ftl1, ctl };
	            //MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
	            //cfg.setTemplateLoader(mtl);
	            
	            // Build the data-model
	            Map<String, Object> data = new HashMap<String, Object>();
	            data.put("messages", "Hello World!");
	 
	            //List parsing 
	            List<String> countries = new ArrayList<String>();
	            countries.add("India");
	            countries.add("United States");
	            countries.add("Germany");
	            countries.add("France");
	             
	            data.put("countries", countries);
	 
	             
	            // Console output
	            Writer out = new OutputStreamWriter(System.out);
	            template.process(data, out);
	            out.flush();
	 
	            // File output
	            Writer file = new FileWriter (new File("/home/sisily/workspace_Git/helloWorldOutput.html"));
	            template.process(data, file);
	            file.flush();
	            file.close();
	             
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (TemplateException e) {
	            e.printStackTrace();
	        }
		}
	
	    public static void main(String[] args) {
	         HelloWorld hw = new HelloWorld();
	         hw.generateTemplate();
	       
	    }

	
	
	//</string></string></string,></string,>
//	}
		
}
