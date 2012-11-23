package ClassificationModel;

import DBUtility.MongoDBUtility.MongoDB;
import DBUtility.iDBUtility;
import DataModel.ComputerShopping;
import DecisionTree.DecisionTree;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-23
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class GetModelForComputerShopping implements iGetModel{
    DecisionTree decisionTree = null;

    @Override
    public DecisionTree getModel() {
        iDBUtility dbUtility = new MongoDB();
        iGetClassificationModel getClassificationModel = new GetClassificationImpl();

        dbUtility.getConnection();
        List<DBObject> dbObjectList = dbUtility.getAllData();
        dbUtility.disConnection();
        List<ComputerShopping> csList = new ArrayList<ComputerShopping>();
        for (DBObject ob: dbObjectList){
            ComputerShopping cs = new ComputerShopping();
            cs.changeFromMongoDB(ob);
            csList.add(cs);
        }

        decisionTree = getClassificationModel.getClassificationDecisionTree(csList, 1, 0);

        return decisionTree;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
