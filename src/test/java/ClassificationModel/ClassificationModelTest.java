package ClassificationModel;

import DataModelProcessed.Age;
import DataModelProcessed.ComputerShoppingProcessed;
import DataModelProcessed.Gender;
import DataModelProcessed.Income;
import DecisionTree.DecisionTree;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-11
 * Time: 上午3:03
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationModelTest extends TestCase{

    public void testGetDecisionTreeModel(){
        List<ComputerShoppingProcessed> data = getTestList();
        ClassificationModel cm = new ClassificationModel();
        DecisionTree dtree = cm.getDecisionTreeModel(data, 1, 0);
        dtree.printTree();
    }

    private List<ComputerShoppingProcessed> getTestList() {
        List<ComputerShoppingProcessed> cspList = new ArrayList<ComputerShoppingProcessed>();

        for (int i = 0; i < 50; ++i){
            ComputerShoppingProcessed csp = new ComputerShoppingProcessed();

            int label = randApple(1, 3);
            if (label == 1){
                csp.setAge(Age.CHILDHOOD);
            } else if (label == 2){
                csp.setAge(Age.JUNIOR);
            } else {
                csp.setAge(Age.SENIOR);
            }

            label = randApple(1, 2);
            if (label == 1){
                csp.setGender(Gender.FEMALE);
            } else {
                csp.setGender(Gender.MALE);
            }

            label = randApple(1, 3);
            if (label == 1){
                csp.setIncome(Income.LOW);
            } else if (label == 2){
                csp.setIncome(Income.MEDIAN);
            } else {
                csp.setIncome(Income.HIGH);
            }

            label = randApple(1, 2);
            if (label == 1){
                csp.setBuyOrNot("yes");
            } else {
                csp.setBuyOrNot("no");
            }

            cspList.add(csp);
        }

        return cspList;
    }

    private int randApple(int min, int max){
        Random ran = new Random();

        return ran.nextInt(max)%(max-min+1) + min;
    }
}
