package freeMarker;



	
import java.io.File;
import java.io.FileWriter;
 
import com.northconcepts.datapipeline.core.Record;
import com.northconcepts.datapipeline.core.RecordList;
import com.northconcepts.datapipeline.job.JobTemplate;
import com.northconcepts.datapipeline.memory.MemoryReader;
import com.northconcepts.datapipeline.template.TemplateWriter;
 
public class freeMarker {
     
    public static void main(String[] args) throws Throwable {
 
        Record record1 = new Record();
        record1.getField("stageName", true).setValue("John Wayne");
        record1.getField("realName", true).setValue("Marion Robert Morrison");
        record1.getField("gender", true).setValue("male");
        record1.getField("city", true).setValue("Winterset");
        record1.getField("balance", true).setValue(156.35);
 
        Record record2 = new Record();
        record2.getField("stageName", true).setValue("Spiderman");
        record2.getField("realName", true).setValue("Peter Parker");
        record2.getField("gender", true).setValue("male");
        record2.getField("city", true).setValue("New York");
        record2.getField("balance", true).setValue(-0.96);
                
        MemoryReader reader = new MemoryReader(new RecordList(record1, record2));
         
 
        TemplateWriter writer = new TemplateWriter(new FileWriter("C:/Users/syuniyar/Eclipse_WS/Trial/credit-balance-04.html"));
        writer.setFieldNamesInFirstRow(false);
        writer.getConfiguration().setDirectoryForTemplateLoading(new File("C:/Users/syuniyar/Eclipse_WS/Trial"));
//        writer.setHeaderTemplate("WriteHtmlUsingFreeMarkerTemplates-header.html");  // No header in this example
       // writer.setFooterTemplate("WriteHtmlUsingFreeMarkerTemplates-footer.html");
        writer.setDetailTemplate("WriteHtmlUsingFreeMarkerTemplates-detail.html");
         
        JobTemplate.DEFAULT.transfer(reader, writer);
    }
 
}