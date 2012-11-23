package ClassificationModel;

import ClassificationModel.SplitCalculationImpl.EntropyImpl;
import DataModelProcessed.Age;
import DataModelProcessed.ComputerShoppingProcessed;
import DataModelProcessed.Gender;
import DataModelProcessed.Income;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-10
 * Time: 上午2:05
 * To change this template use File | Settings | File Templates.
 */
public class EntropyImplTest extends TestCase {
    public void testGetSplitValue(){
        iSplitCalculation splitCalculation = new EntropyImpl();
        List<ComputerShoppingProcessed> testList = getTestList();
        double entropy = splitCalculation.getSplitValue(testList, "gender");
        System.out.println(entropy);
    }

    private List<ComputerShoppingProcessed> getTestList() {
        List<ComputerShoppingProcessed> cspList = new ArrayList<ComputerShoppingProcessed>();

        ComputerShoppingProcessed csp1 = new ComputerShoppingProcessed();
        csp1.setAge(Age.CHILDHOOD);
        csp1.setGender(Gender.FEMALE);
        csp1.setIncome(Income.LOW);
        csp1.setBuyOrNot("no");
        cspList.add(csp1);

        ComputerShoppingProcessed csp2 = new ComputerShoppingProcessed();
        csp2.setAge(Age.SENIOR);
        csp2.setGender(Gender.FEMALE);
        csp2.setIncome(Income.HIGH);
        csp2.setBuyOrNot("no");
        cspList.add(csp2);

        ComputerShoppingProcessed csp3 = new ComputerShoppingProcessed();
        csp3.setAge(Age.CHILDHOOD);
        csp3.setGender(Gender.MALE);
        csp3.setIncome(Income.MEDIAN);
        csp3.setBuyOrNot("no");
        cspList.add(csp3);

        ComputerShoppingProcessed csp4 = new ComputerShoppingProcessed();
        csp4.setAge(Age.JUNIOR);
        csp4.setGender(Gender.MALE);
        csp4.setIncome(Income.HIGH);
        csp4.setBuyOrNot("yes");
        cspList.add(csp4);

        return cspList;
    }
}
