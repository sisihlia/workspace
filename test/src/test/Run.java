package test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Run {

	public static void main(String[] args) {
		 try{   // get the collection name in indicated database
			   mongodb mdb = new mongodb();
			   test test = new test();
			   MongoClient mongo = new MongoClient("localhost", 27017);
			  
			DB db = mongo.getDB("catdemo");
			 DBCollection collection = db.getCollection("items");
			 
		String y = mdb.selectSingleRecordAndFieldByRecordNumber(collection);
		
			test.postConnection(y);
			//test.getValue();
			test.observeValue();
			 
		 }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }

	}

}
