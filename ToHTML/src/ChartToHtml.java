import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
public class ChartToHtml {

	public void generateChartTemplate(){
		
		
        try {
        	
       
        	 Version version = new Version(2, 3, 22);
   	      
 			Configuration config = new Configuration(version);
 			
        	config.setDirectoryForTemplateLoading(new File("/home/sisily/workspace_Git/ToHTML"));
        	
        	
        	Template template = config.getTemplate("exampleReport.html");
          
        	int failedVal = 8;
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("failedVal", failedVal);
            data.put("PassedVal", "5");
          
             
            // Console output
            Writer out = new OutputStreamWriter(System.out);
            template.process(data, out);
            out.flush();
 
            // File output
            Writer file = new FileWriter (new File("/home/sisily/workspace_Git/ToHTML/ChartHTML.html"));
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
       ChartToHtml chm = new ChartToHtml();
       chm.generateChartTemplate();
       
    }



//</string></string></string,></string,>
//}
	
}
