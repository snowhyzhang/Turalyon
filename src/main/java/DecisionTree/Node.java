package DecisionTree;

import DataModelProcessed.ComputerShoppingProcessed;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-9
 * Time: 上午4:16
 * To change this template use File | Settings | File Templates.
 */
public class Node {

    private int nodeNo = 0;
    private String label;
    private boolean isLeaf = false;
    private Map<String, Integer> nodeMapper = null;

    public int getNextNode(String testLabel){
        return nodeMapper.get(testLabel.toUpperCase());
    }

    public void insertNextNode(String label, int i){
        if (nodeMapper == null)
            nodeMapper = new HashMap<String, Integer>();
        nodeMapper.put(label.toUpperCase(), i);
    }

    public int getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(int nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
