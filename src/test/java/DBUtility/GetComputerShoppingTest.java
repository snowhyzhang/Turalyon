package DBUtility;

import DBUtility.MongoDBUtility.MongoDB;
import DataModel.ComputerShopping;
import com.mongodb.DBObject;
import junit.framework.TestCase;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-21
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class GetComputerShoppingTest extends TestCase{
    public void testGetComputerShopping(){
        List<ComputerShopping> csList = new ArrayList<ComputerShopping>();

        iDBUtility loader = new MongoDB();
        loader.getConnection();
        List<DBObject> dbList = loader.getAllData();
        loader.disConnection();
        for (DBObject ob: dbList){
            ComputerShopping cs = new ComputerShopping();
            cs.changeFromMongoDB(ob);
            csList.add(cs);
        }

        System.out.println("Total data: " + csList.size() + "\n************");
        for (int i = 0; i < csList.size(); ++i){
            System.out.println("Data No." + i);
            System.out.println("Age: " + csList.get(i).getAge());
            System.out.println("Gender: " + csList.get(i).getGender());
            System.out.println("Income: " + csList.get(i).getIncome());
            System.out.println("BuyOrNot: " + csList.get(i).getBuyOrNot());
            System.out.println("*****************");
        }
    }
}
