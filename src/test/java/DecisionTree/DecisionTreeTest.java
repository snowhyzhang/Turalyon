package DecisionTree;

import DataModelProcessed.Age;
import DataModelProcessed.ComputerShoppingProcessed;
import DataModelProcessed.Gender;
import DataModelProcessed.Income;
import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-9
 * Time: 上午8:26
 * To change this template use File | Settings | File Templates.
 */
public class DecisionTreeTest extends TestCase{

    DecisionTree dtree = new DecisionTree();

    public void testDecisionTree(){
        decisionTreeBuild();

        ComputerShoppingProcessed csp1= new ComputerShoppingProcessed();
        csp1.setAge(Age.JUNIOR);
        csp1.setGender(Gender.FEMALE);
        csp1.setIncome(Income.HIGH);
        String result1 = dtree.getLabel(csp1, 0);
        assertEquals("yes", result1);

        ComputerShoppingProcessed csp2 = new ComputerShoppingProcessed();
        csp2.setAge(Age.JUNIOR);
        csp2.setGender(Gender.MALE);
        csp2.setIncome(Income.MEDIAN);
        String result2 = dtree.getLabel(csp2, 0);
        assertEquals("yes", result2);

        ComputerShoppingProcessed csp3 = new ComputerShoppingProcessed();
        csp3.setAge(Age.SENIOR);
        csp3.setGender(Gender.FEMALE);
        csp3.setIncome(Income.LOW);
        String result3 = dtree.getLabel(csp3, 0);
        assertEquals("no", result3);

        dtree.printTree();
    }

    public void decisionTreeBuild(){
        Node genderNode = new Node();
        genderNode.setNodeNo(dtree.createNewNodeNo());
        genderNode.setLabel("gender");
        genderNode.setLeaf(false);
        dtree.insertNode(genderNode);

        Node incomeNode = new Node();
        incomeNode.setNodeNo(dtree.createNewNodeNo());
        incomeNode.setLabel("income");
        incomeNode.setLeaf(false);
        genderNode.insertNextNode("female", incomeNode.getNodeNo());
        dtree.insertNode(incomeNode);

        Node ageNode = new Node();
        ageNode.setNodeNo(dtree.createNewNodeNo());
        ageNode.setLabel("age");
        ageNode.setLeaf(false);
        genderNode.insertNextNode("male", ageNode.getNodeNo());
        dtree.insertNode(ageNode);

        Node result1 = new Node();
        result1.setNodeNo(dtree.createNewNodeNo());
        result1.setLeaf(true);
        result1.setLabel("no");
        incomeNode.insertNextNode("low", result1.getNodeNo());
        dtree.insertNode(result1);

        Node result2 = new Node();
        result2.setNodeNo(dtree.createNewNodeNo());
        result2.setLeaf(true);
        result2.setLabel("no");
        incomeNode.insertNextNode("median", result2.getNodeNo());
        dtree.insertNode(result2);

        Node result3 = new Node();
        result3.setNodeNo(dtree.createNewNodeNo());
        result3.setLeaf(true);
        result3.setLabel("yes");
        incomeNode.insertNextNode("high", result3.getNodeNo());
        dtree.insertNode(result3);

        Node result4 = new Node();
        result4.setNodeNo(dtree.createNewNodeNo());
        result4.setLeaf(true);
        result4.setLabel("no");
        ageNode.insertNextNode("childhood", result4.getNodeNo());
        dtree.insertNode(result4);

        Node result5 = new Node();
        result5.setNodeNo(dtree.createNewNodeNo());
        result5.setLeaf(true);
        result5.setLabel("yes");
        ageNode.insertNextNode("junior", result5.getNodeNo());
        dtree.insertNode(result5);

        Node result6 = new Node();
        result6.setNodeNo(dtree.createNewNodeNo());
        result6.setLeaf(true);
        result6.setLabel("no");
        ageNode.insertNextNode("senior", result6.getNodeNo());
        dtree.insertNode(result6);
    }
}






















