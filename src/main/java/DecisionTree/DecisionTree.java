package DecisionTree;

import DataModelProcessed.ComputerShoppingProcessed;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-9
 * Time: 上午4:28
 * To change this template use File | Settings | File Templates.
 */
public class DecisionTree {
    int nodeCurrentNumber = 0;
    int nodeTotalNumber = 0;
    Map<Integer, Node> treeMapper = null;

    public void insertNode(Node node){
        if (treeMapper == null)
            treeMapper = new HashMap<Integer, Node>();
        treeMapper.put(node.getNodeNo(), node);
        ++nodeTotalNumber;
    }


    public String getLabel(ComputerShoppingProcessed csp, int nodeNo){
        Node currentNode = treeMapper.get(nodeNo);
        if (currentNode == null){
            return null;
        }
        if (currentNode.isLeaf())
        {
            return currentNode.getLabel();
        } else {
            int nodeNext = 0;
            String testLabel = currentNode.getLabel();
            if (testLabel.equalsIgnoreCase("age")){
                nodeNext = currentNode.getNextNode(csp.getAge().toString());
            } else if (testLabel.equalsIgnoreCase("gender")){
                nodeNext = currentNode.getNextNode(csp.getGender().toString());
            } else if (testLabel.equalsIgnoreCase("income")){
                nodeNext = currentNode.getNextNode(csp.getIncome().toString());
            }
            return getLabel(csp, nodeNext);
        }
    }

    public int getNodeCurrentNumber() {
        return nodeCurrentNumber;
    }

    public void setNodeCurrentNumber(int nodeCurrentNumber) {
        this.nodeCurrentNumber = nodeCurrentNumber;
    }

    public int getNodeTotalNumber() {
        return nodeTotalNumber;
    }

    public void setNodeTotalNumber(int nodeTotalNumber) {
        this.nodeTotalNumber = nodeTotalNumber;
    }

    public int createNewNodeNo(){
        int tmp = this.nodeCurrentNumber;
        ++this.nodeCurrentNumber;
        return tmp;
    }

    public void printTree(){
        for (int i = 0; i < treeMapper.size(); ++i){
            Node node = treeMapper.get(i);
            System.out.println("*******");
            System.out.println("Node No.: " + node.getNodeNo());
            System.out.println("Node Label: " + node.getLabel());
            if (node.getLabel().equalsIgnoreCase("age")){
                System.out.println("Age childhood node: " + node.getNextNode("childhood"));
                System.out.println("Age junior node: " + node.getNextNode("junior"));
                System.out.println("Age senior node: " + node.getNextNode("senior"));
            } else if (node.getLabel().equalsIgnoreCase("gender")){
                System.out.println("Gender female node: " + node.getNextNode("female"));
                System.out.println("Gender male node: " + node.getNextNode("male"));
            } else if (node.getLabel().equalsIgnoreCase("income")){
                System.out.println("Income low node: " + node.getNextNode("low"));
                System.out.println("Income median node: " + node.getNextNode("median"));
                System.out.println("Income high node: " + node.getNextNode("high"));
            }
        }
    }
}
