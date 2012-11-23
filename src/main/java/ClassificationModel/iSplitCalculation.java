package ClassificationModel;

import DataModelProcessed.ComputerShoppingProcessed;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-10
 * Time: 上午1:14
 * To change this template use File | Settings | File Templates.
 */
public interface iSplitCalculation {
    public double getSplitValue(List<ComputerShoppingProcessed> Data, String testLabel);
}
