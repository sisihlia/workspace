package ScriptPack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
/**
 * @author syuniyar
 *01/06/15 syuniyar			add comments
 *26/06/15 syuniyar			modify class association
 */
class MyFormatter extends Formatter {
	/**define the format for log file
	 *
	 * 
	 */
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
 
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder(1000);
       // builder.append("\n");
        builder.append(df.format(new Date(record.getMillis())));
        builder.append("[").append(record.getSourceClassName()).append(".");
        builder.append(record.getSourceMethodName()).append("]");
        builder.append("\n");
        builder.append("[").append(record.getLevel()).append("]");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
    }
 
    public String getHead(Handler h) {
        return super.getHead(h);
    }
 
    public String getTail(Handler h) {
        return super.getTail(h);
    }
}
