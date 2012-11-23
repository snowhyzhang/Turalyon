package ClassificationModel;

import DataModel.ComputerShopping;
import DataModelProcessed.ComputerShoppingProcessed;
import DecisionTree.DecisionTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-11
 * Time: 上午5:51
 * To change this template use File | Settings | File Templates.
 */
public class GetClassificationImpl implements iGetClassificationModel {

    @Override
    public DecisionTree getClassificationDecisionTree(List<ComputerShopping> data, int depth, double splitThreshold) {
        ClassificationModel cm = new ClassificationModel();

        List<ComputerShoppingProcessed>  cspList = DataCovert(data);

        return cm.getDecisionTreeModel(cspList, depth, splitThreshold);  //To change body of implemented methods use File | Settings | File Templates.
    }

    private List<ComputerShoppingProcessed> DataCovert(List<ComputerShopping> data) {
        if (data == null || data.isEmpty()){
            return null;
        }
        List<ComputerShoppingProcessed> cspList = new ArrayList<ComputerShoppingProcessed>();
        for (ComputerShopping cs: data){
            ComputerShoppingProcessed csp = new ComputerShoppingProcessed();
            csp.processData(cs);
            cspList.add(csp);
        }
        return cspList;  //To change body of created methods use File | Settings | File Templates.
    }
}
