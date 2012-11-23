package ClassificationModel;

import ClassificationModel.SplitCalculationImpl.EntropyImpl;
import DataModelProcessed.Age;
import DataModelProcessed.ComputerShoppingProcessed;
import DataModelProcessed.Gender;
import DataModelProcessed.Income;
import DecisionTree.DecisionTree;
import DecisionTree.Node;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-10
 * Time: 上午12:54
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationModel {
    private DecisionTree decisionTree = new DecisionTree();
    private int currentDepth = 0;
    private int MaxDepth;
    private double splitThreshold;

    iSplitCalculation splitCalculation = new EntropyImpl();
    Queue<Node> processingNodeQueue = new ArrayDeque<Node>();
    Queue<String> testLabelQueue = new ArrayDeque<String>();
    Map<Integer, List<List<ComputerShoppingProcessed>>> dataMapper = new HashMap<Integer, List<List<ComputerShoppingProcessed>>>();


    public DecisionTree getDecisionTreeModel(List<ComputerShoppingProcessed> data, int depth, double splitThreshold){
        Node headNode = new Node();
        String testLabel = null;
        MaxDepth = depth;
        this.splitThreshold = splitThreshold;

        double[] splitCal = getSplitCal(data);
        int bestSplit = getBestSplit(splitCal);

        testLabel = getLabelName(headNode, bestSplit);
        List<List<ComputerShoppingProcessed>> subDataSet = getDataSplit(data, testLabel);
        headNode.setNodeNo(decisionTree.createNewNodeNo());
        headNode.setLeaf(false);
        decisionTree.insertNode(headNode);

        if (splitThreshold >= splitCal[bestSplit]){
            createLeaf(headNode, testLabel, subDataSet);
        } else {
            processingNodeQueue.add(headNode);
            dataMapper.put(headNode.getNodeNo(), subDataSet);
            ++currentDepth;
            testLabelQueue.add(testLabel);
            decisionTreeGrowth();
        }

        return decisionTree;
    }

    private void decisionTreeGrowth() {
        int countNode = processingNodeQueue.size();
        while (!processingNodeQueue.isEmpty()){
            if (countNode == 0){
                ++currentDepth;
                countNode = processingNodeQueue.size();
            }
            Node processingNode = processingNodeQueue.poll();
            --countNode;
            String testLabel = testLabelQueue.poll();
            List<List<ComputerShoppingProcessed>> subDataSet = dataMapper.get(processingNode.getNodeNo());
            int order = 0;
            for (List<ComputerShoppingProcessed> cspList: subDataSet){
                if (cspList == null || cspList.isEmpty()){
                    Node nullNode = new Node();
                    nullNode.setNodeNo(decisionTree.createNewNodeNo());
                    nullNode.setLabel("Unknown");
                    nullNode.setLeaf(true);
                    decisionTree.insertNode(nullNode);
                    insertProcessingNode(testLabel, order, processingNode, nullNode);
                    ++order;
                    continue;
                }
                Node subNode = new Node();
                double[] splitCal = getSplitCal(cspList);
                int bestSplit = getBestSplit(splitCal);

                String testSubLabel = getLabelName(subNode, bestSplit);
                List<List<ComputerShoppingProcessed>> newSubDataSet = getDataSplit(cspList, testSubLabel);
                subNode.setNodeNo(decisionTree.createNewNodeNo());
                subNode.setLeaf(false);
                decisionTree.insertNode(subNode);

                insertProcessingNode(testLabel, order, processingNode, subNode);
                ++order;

                if (splitThreshold >= splitCal[bestSplit] || currentDepth >= MaxDepth){
                    createLeaf(subNode, testSubLabel, newSubDataSet);
                } else {
                    processingNodeQueue.add(subNode);
                    testLabelQueue.add(testSubLabel);
                    dataMapper.put(subNode.getNodeNo(), newSubDataSet);
                }
            }
        }
    }

    private void insertProcessingNode(String testLabel, int order, Node processingNode, Node subNode) {
        if (testLabel.equalsIgnoreCase("age")){
            if (order == 0){
                processingNode.insertNextNode("childhood", subNode.getNodeNo());
            } else if (order == 1){
                processingNode.insertNextNode("junior", subNode.getNodeNo());
            } else {
                processingNode.insertNextNode("senior", subNode.getNodeNo());
            }
        } else if (testLabel.equalsIgnoreCase("gender")){
            if (order == 0){
                processingNode.insertNextNode("female", subNode.getNodeNo());
            } else {
                processingNode.insertNextNode("male", subNode.getNodeNo());
            }
        } else if (testLabel.equalsIgnoreCase("income")){
            if (order == 0){
                processingNode.insertNextNode("low", subNode.getNodeNo());
            } else if (order == 1){
                processingNode.insertNextNode("median", subNode.getNodeNo());
            } else {
                processingNode.insertNextNode("high", subNode.getNodeNo());
            }
        }
    }

    private double[] getSplitCal(List<ComputerShoppingProcessed> data) {
        double[] splitCal = new double[3];
        splitCal[0] = splitCalculation.getSplitValue(data, "age");
        splitCal[1] = splitCalculation.getSplitValue(data, "gender");
        splitCal[2] = splitCalculation.getSplitValue(data, "income");
        return splitCal;
    }

    private String getLabelName(Node headNode,  int bestSplit) {
        if (bestSplit == 0){
            headNode.setLabel("age");
            return "age";
        } else if (bestSplit == 1){
            headNode.setLabel("gender");
            return "gender";
        } else {
            headNode.setLabel("income");
            return "income";
        }
    }


    private void createLeaf(Node node, String testLabel, List<List<ComputerShoppingProcessed>> subDataSet) {
        int order = 0;
        for (List<ComputerShoppingProcessed> cspList: subDataSet){
            Node leafNode = new Node();
            leafNode.setNodeNo(decisionTree.createNewNodeNo());
            leafNode.setLeaf(true);
            leafNode.setLabel(getClassification(cspList));
            node.insertNextNode(getSubLabel(testLabel, order), leafNode.getNodeNo());
            decisionTree.insertNode(leafNode);
            ++order;
        }
    }

    private String getSubLabel(String testLabel, int order) {
        if (testLabel.equalsIgnoreCase("age")){
            if (order == 0){
                return "childhood";
            } else if (order == 1){
                return "junior";
            } else if (order == 2){
                return "senior";
            }
        } else if (testLabel.equalsIgnoreCase("gender")){
            if (order == 0){
                return "female";
            } else if (order == 1){
                return "male";
            }
        } else if (testLabel.equalsIgnoreCase("income")){
            if (order == 0){
                return "low";
            } else if (order == 1){
                return "median";
            } else if (order == 2){
                return "high";
            }
        }
        return null;
    }

    private String getClassification(List<ComputerShoppingProcessed> cspList) {
        int yesData = 0;
        int noData = 0;
        for (ComputerShoppingProcessed csp: cspList){
            if (csp.getBuyOrNot().equalsIgnoreCase("yes")){
                ++yesData;
            } else {
                ++noData;
            }
        }
        if (yesData >= noData){
            return "yes";
        } else {
            return "no";
        }
    }

    private List<List<ComputerShoppingProcessed>> getDataSplit(List<ComputerShoppingProcessed> data, String testLabel) {
        if (testLabel == null)
            return null;
        List<List<ComputerShoppingProcessed>> splitList = new ArrayList<List<ComputerShoppingProcessed>>();
        if (testLabel.equalsIgnoreCase("age")){
            List<ComputerShoppingProcessed> ageChildList = new ArrayList<ComputerShoppingProcessed>();
            List<ComputerShoppingProcessed> ageJuniorList = new ArrayList<ComputerShoppingProcessed>();
            List<ComputerShoppingProcessed> ageSeniorList = new ArrayList<ComputerShoppingProcessed>();
            for (ComputerShoppingProcessed csp: data){
                if (csp.getAge().equals(Age.CHILDHOOD)){
                    ageChildList.add(csp);
                } else if (csp.getAge().equals(Age.JUNIOR)){
                    ageJuniorList.add(csp);
                } else {
                    ageSeniorList.add(csp);
                }
            }
            splitList.add(ageChildList);
            splitList.add(ageJuniorList);
            splitList.add(ageSeniorList);
        } else if (testLabel.equalsIgnoreCase("gender")){
            List<ComputerShoppingProcessed> genderFemale = new ArrayList<ComputerShoppingProcessed>();
            List<ComputerShoppingProcessed> genderMale = new ArrayList<ComputerShoppingProcessed>();
            for (ComputerShoppingProcessed csp: data){
                if (csp.getGender().equals(Gender.FEMALE)){
                    genderFemale.add(csp);
                } else {
                    genderMale.add(csp);
                }
            }
            splitList.add(genderFemale);
            splitList.add(genderMale);
        } else if (testLabel.equalsIgnoreCase("income")){
            List<ComputerShoppingProcessed> incomeLow = new ArrayList<ComputerShoppingProcessed>();
            List<ComputerShoppingProcessed> incomeMedian = new ArrayList<ComputerShoppingProcessed>();
            List<ComputerShoppingProcessed> incomeHigh = new ArrayList<ComputerShoppingProcessed>();
            for (ComputerShoppingProcessed csp: data){
                if (csp.getIncome().equals(Income.LOW)){
                    incomeLow.add(csp);
                } else if (csp.getIncome().equals(Income.MEDIAN)){
                    incomeMedian.add(csp);
                } else {
                    incomeHigh.add(csp);
                }
            }
            splitList.add(incomeLow);
            splitList.add(incomeMedian);
            splitList.add(incomeHigh);
        }
        return splitList;
    }

    private int getBestSplit(double[] splitCal) {
        double bestSplitScore = splitCal[0];
        int bestSplit = 0;
        for (int i = 1; i < splitCal.length; ++i){
            if (splitCal[i] < bestSplitScore){
                bestSplitScore = splitCal[i];
                bestSplit = i;
            }
        }
        return bestSplit;
    }
}
