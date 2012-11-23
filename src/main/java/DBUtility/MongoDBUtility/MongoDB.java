package DBUtility.MongoDBUtility;

import DBUtility.iDBUtility;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-9
 * Time: 上午3:13
 * To change this template use File | Settings | File Templates.
 */
public class MongoDB implements iDBUtility {
    private final String DB_HOST = "localhost";
    private final int DB_PORT = 27017;
    private final String DB_NAME = "mytest";
    private final String COLLECTION_NAME = "ComputerShopping";


    private DB mongoDB = null;
    private Mongo mongo = null;

    @Override
    public void getConnection() {
        try {
            mongo = new Mongo(DB_HOST, DB_PORT);
            mongoDB = mongo.getDB(DB_NAME);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getData(String SQL) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List getAllData() {
        DBCollection resultCollection = mongoDB.getCollection(COLLECTION_NAME);
        DBCursor cursor = resultCollection.find();
        List<DBObject> dataList = new ArrayList<DBObject>();
        while (cursor.hasNext()){
            DBObject dbObject = cursor.next();
            dataList.add(dbObject);
        }
        return dataList;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void disConnection() {
        if (mongo != null){
            mongo.close();
        }
    }
}
