package ClassificationModel;

import DataModel.ComputerShopping;
import DecisionTree.DecisionTree;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-11
 * Time: 上午1:41
 * To change this template use File | Settings | File Templates.
 */
public interface iGetClassificationModel {
    public DecisionTree getClassificationDecisionTree(List<ComputerShopping> data, int depth, double splitThreshold);
}
