package ClassificationModel.SplitCalculationImpl;

import ClassificationModel.iSplitCalculation;
import DataModelProcessed.Age;
import DataModelProcessed.ComputerShoppingProcessed;
import DataModelProcessed.Gender;
import DataModelProcessed.Income;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-10
 * Time: 上午1:17
 * To change this template use File | Settings | File Templates.
 */
public class EntropyImpl implements iSplitCalculation {

    @Override
    public double getSplitValue(List<ComputerShoppingProcessed> data, String testLabel) {
        double entropy = 0;
        int[][] splitGroup = null;
        if (testLabel.equalsIgnoreCase("age")){
            splitGroup = getAgeSplitGroup(data);
        } else if (testLabel.equalsIgnoreCase("gender")){
            splitGroup = getGenderSplitGroup(data);
        } else if (testLabel.equalsIgnoreCase("income")){
            splitGroup = getIncomeSplitGroup(data);
        }

        for (int i = 0; i < splitGroup.length; ++i){
            double tmpEntropy = 0;
            int j = 0;
            for (; j < splitGroup[i].length - 1; ++j){
                if (splitGroup[i][j] == 0)
                    continue;
                else{
                    double tmp = (double)splitGroup[i][j] / splitGroup[i][splitGroup[i].length - 1];
                    tmpEntropy = tmpEntropy + (-1 * (tmp * (Math.log(tmp) / Math.log(2))));
                }
            }
            entropy = entropy + tmpEntropy * ((double)splitGroup[i][j] / data.size());
        }

        return entropy;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private int[][] getIncomeSplitGroup(List<ComputerShoppingProcessed> data) {
        int[][] result = new int[3][3];
        for (ComputerShoppingProcessed csp: data){
            if (csp.getIncome().equals(Income.LOW)){
                if (csp.getBuyOrNot().equalsIgnoreCase("yes")){
                    ++result[0][0];
                } else {
                    ++result[0][1];
                }
                ++result[0][2];
            } else if (csp.getIncome().equals(Income.MEDIAN)){
                if (csp.getBuyOrNot().equalsIgnoreCase("yes")){
                    ++result[1][0];
                } else {
                    ++result[1][1];
                }
                ++result[1][2];
            } else if (csp.getIncome().equals(Income.HIGH)){
                if (csp.getBuyOrNot().equalsIgnoreCase("yes")){
                    ++result[2][0];
                } else {
                    ++result[2][1];
                }
                ++result[2][2];
            }
        }
        return result;
    }

    private int[][] getGenderSplitGroup(List<ComputerShoppingProcessed> data) {
        int[][] result = new int[2][3];
        for (ComputerShoppingProcessed csp: data){
            if (csp.getGender().equals(Gender.FEMALE)){
                if (csp.getBuyOrNot().equalsIgnoreCase("yes")){
                    ++result[0][0];
                } else {
                    ++result[0][1];
                }
                ++result[0][2];
            } else if (csp.getGender().equals(Gender.MALE)){
                if (csp.getBuyOrNot().equalsIgnoreCase("yes")){
                    ++result[1][0];
                } else {
                    ++result[1][1];
                }
                ++result[1][2];
            }
        }
        return result;
    }

    private int[][] getAgeSplitGroup(List<ComputerShoppingProcessed> data) {
        int[][] result = new int[3][3];
        for (ComputerShoppingProcessed csp: data){
            if (csp.getAge().equals(Age.CHILDHOOD)){
                if (csp.getBuyOrNot().equalsIgnoreCase("yes")){
                    ++result[0][0];
                } else {
                    ++result[0][1];
                }
                ++result[0][2];
            } else if (csp.getAge().equals(Age.JUNIOR)) {
                if (csp.getBuyOrNot().equalsIgnoreCase("yes")){
                    ++result[1][0];
                } else {
                    ++result[1][1];
                }
                ++result[1][2];
            }  else if (csp.getAge().equals(Age.SENIOR)) {
                if (csp.getBuyOrNot().equalsIgnoreCase("yes")){
                    ++result[2][0];
                } else {
                    ++result[2][1];
                }
                ++result[2][2];
            }
        }
        return result;
    }
}
