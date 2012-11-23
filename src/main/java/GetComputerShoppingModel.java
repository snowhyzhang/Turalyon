import ClassificationModel.GetModelForComputerShopping;
import DecisionTree.DecisionTree;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-23
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class GetComputerShoppingModel {

    public static void main(String[] args){
        GetModelForComputerShopping getModelForComputerShopping = new GetModelForComputerShopping();
        DecisionTree decisionTree = getModelForComputerShopping.getModel();

        decisionTree.printTree();
    }
}
