
// Homework 7
// Treenode class for 20 Questions program.

public class QuestionNode {
    public String data;
    public QuestionNode left;
    public QuestionNode right;
                
    // constructs a leaf node with given data
    public QuestionNode(String data) {
        this(data, null, null);
    }
                        
    // constructs a branch node with given data, left subtree,
    // right subtree
    public QuestionNode(String data, QuestionNode left, 
                       QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
