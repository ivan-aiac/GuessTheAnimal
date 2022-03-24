package animals;

import animals.node.Animal;
import animals.node.Node;

public class TreeStatistic {
    private String rootContent;
    private int height;
    private int nodes;
    private int animals;
    private int statements;
    private int minAnimalDepth;
    private float avgAnimalDepth;

    public TreeStatistic(Node rootNode) {
        rootContent = rootNode.getContent();
        countNumberOfNodes(rootNode);
        height = calculateTreeHeight(rootNode);
        minAnimalDepth = height;
        calculateMinAndAvgAnimalsDepth(rootNode, 1);
        avgAnimalDepth = avgAnimalDepth / animals;
        height = height - 1;
    }

    public String getRootContent() {
        return rootContent;
    }

    public int getHeight() {
        return height;
    }

    public int getNodes() {
        return nodes;
    }

    public int getAnimals() {
        return animals;
    }

    public int getStatements() {
        return statements;
    }

    public int getMinAnimalDepth() {
        return minAnimalDepth;
    }

    public double getAvgAnimalDepth() {
        return avgAnimalDepth;
    }

    private void countNumberOfNodes(Node node) {
        if (node != null) {
            countNumberOfNodes(node.getLeftNode());
            countNumberOfNodes(node.getRightNode());
            nodes++;
            if (node instanceof Animal) {
                animals++;
            } else {
                statements++;
            }
        }
    }

    private int calculateTreeHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = calculateTreeHeight(node.getLeftNode());
        int rightHeight = calculateTreeHeight(node.getRightNode());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private void calculateMinAndAvgAnimalsDepth(Node node, int height) {
        if (node != null) {
            calculateMinAndAvgAnimalsDepth(node.getLeftNode(), height + 1);
            calculateMinAndAvgAnimalsDepth(node.getRightNode(), height + 1);
            if (node instanceof Animal) {
                minAnimalDepth = Math.min(minAnimalDepth, height - 1);
                avgAnimalDepth += height - 1;
            }
        }
    }


}
