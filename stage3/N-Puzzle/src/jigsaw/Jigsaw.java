package jigsaw;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

/** class Jigsaw
 * Used to complish the jigsaw
 * @author Shawn Dai
 */
public class Jigsaw {
  private JigsawNode beginJNode;
  private JigsawNode endJNode;
  private JigsawNode currentJNode;
  // save detected but unvisited nodes
  private Vector<JigsawNode> openList;
  // save visited nodes
  private Vector<JigsawNode> closeList;
  // the path of soulution
  private Vector<JigsawNode> solutionPath;
  // completed or not
  private boolean isCompleted;
  // the number of visited nodes
  private int searchedNodesNum;

  /** Constructor
   * @param bNode the begin state
   * @param eNode the target state
   */
  public Jigsaw(JigsawNode bNode, JigsawNode eNode) {
    this.beginJNode = new JigsawNode(bNode);
    this.endJNode = new JigsawNode(eNode);
    this.currentJNode = new JigsawNode(bNode);
    this.openList = new Vector<JigsawNode>();
    this.closeList = new Vector<JigsawNode>();
    this.solutionPath = null;
    this.isCompleted = false;
    this.searchedNodesNum = 0;
  }

  /**
   * scatter the jigsaw
   * @param jNode the begin state
   * @param len number of steps to move randomly
   * @return the state node after scattered
   */
  public static JigsawNode scatter(JigsawNode jNode, int len) {
    int randomDirection;
    len += (int) (Math.random() * 2);
    JigsawNode jigsawNode = new JigsawNode(jNode);
    for (int t = 0; t < len; t++) {
      int[] movable = jigsawNode.canMove();
      do {
        randomDirection = (int) (Math.random() * 4);
      }
      while (0 == movable[randomDirection]);
      jigsawNode.move(randomDirection);
    }
    jigsawNode.setInitial();
    return jigsawNode;
  }

  /**
   * get current state node
   * @return currentJNode
   */
  public JigsawNode getCurrentJNode() {
    return this.currentJNode;
  }

  /**
   * set current state node
   * @param JNode to set
   */
  public void setBeginJNode(JigsawNode jNode) {
    this.beginJNode = jNode;
  }

  /**
   * get begin state node
   * @return beginJNode
   */
  public JigsawNode getBeginJNode() {
    return this.beginJNode;
  }

  /**
   * set current state node
   * @param JNode to set
   */
  public void setEndJNode(JigsawNode jNode) {
    this.endJNode = jNode;
  }

  /**
   * get end state node
   * @return endJNode
   */
  public JigsawNode getEndJNode() {
    return this.endJNode;
  }

  /**
   * completed or not
   * @return true if completed
   */
  public boolean isCompleted() {
    return this.isCompleted;
  }

  /**
   * calculate solution path if completed
   * @return true if completed, false otherwise
   */
  private boolean calSolutionPath() {
    if (!this.isCompleted()) {
      return false;
    } else {
      JigsawNode jNode = this.currentJNode;
      solutionPath = new Vector<JigsawNode>();
      while (jNode != null) {
        solutionPath.addElement(jNode);
        jNode = jNode.getParent();
      }
      return true;
    }
  }

  /**
   * get a string version of solution path
   * @return the string of solution path or information if not completed
   */
  public String getSolutionPath() {
    String str = new String();
    str += "Begin->";
    if (this.isCompleted) {
      for (int i = solutionPath.size() - 1; i >= 0; i--) {
        str += solutionPath.elementAt(i).toString() + "->";
      }
      str += "End";
    } else {
      str = "Jigsaw Not Completed.";
    }
    return str;
  }

  /**
   * get number of searched nodes
   * @return searchedNodesNum
   */
  public int getSearchedNodesNum() {
    return searchedNodesNum;
  }

  /**
   * write the search results into file. Print them on console too.
   * @param pw PrintWriter object
   * @throws IOException
   */
  public void printResult(PrintWriter pw) throws IOException {
    boolean flag = false;
    if(pw == null) {
      pw = new PrintWriter(new FileWriter("Result.txt"));// 将搜索过程写入D://BFSearchDialog.txt
      flag = true;
    }
    if (this.isCompleted == true) {
      // to file
      pw.println("Jigsaw Completed");
      pw.println("Begin state:" + this.getBeginJNode().toString());
      pw.println("End state:" + this.getEndJNode().toString());
      pw.println("Solution Path: ");
      pw.println(this.getSolutionPath());
      pw.println("Total number of searched nodes:" + this.getSearchedNodesNum());
      pw.println("Length of the solution path is:" + this.getCurrentJNode().getNodeDepth());

      // to console
      System.out.println("Jigsaw Completed");
      System.out.println("Begin state:" + this.getBeginJNode().toString());
      System.out.println("End state:" + this.getEndJNode().toString());
      System.out.println("Solution Path: ");
      System.out.println(this.getSolutionPath());
      System.out.println("Total number of searched nodes:" + this.getSearchedNodesNum());
      System.out.println("Length of the solution path is:" + this.getCurrentJNode().getNodeDepth());
    } else {
      // to file
      pw.println("No solution. Jigsaw Not Completed");
      pw.println("Begin state:" + this.getBeginJNode().toString());
      pw.println("End state:" + this.getEndJNode().toString());
      pw.println("Total number of searched nodes:"
          + this.getSearchedNodesNum());

      // to console
      System.out.println("No solution. Jigsaw Not Completed");
      System.out.println("Begin state:" + this.getBeginJNode().toString());
      System.out.println("End state:" + this.getEndJNode().toString());
      System.out.println("Total number of searched nodes:"
          + this.getSearchedNodesNum());
    }

    if (flag) {
      pw.close();
    }
  }

  /**
   * detect all adjacent but unvisited nodes
   * @return vector of all adjacent but unvisited nodes
   */
  private Vector<JigsawNode> findFollowJNodes(JigsawNode jNode) {
    Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();
    JigsawNode tempJNode;
    for(int i = 0; i < 4; i++){
      tempJNode = new JigsawNode(jNode);
      if(tempJNode.move(i) && !this.closeList.contains(tempJNode) && !this.openList.contains(tempJNode))
        followJNodes.addElement(tempJNode);
    }
    return followJNodes;
  }

  /**
   * Insert the node to openList sorted by estimateValue
   * @param jNode to insert
   */
  private void sortedInsertOpenList(JigsawNode jNode) {
    this.estimateValue(jNode);
    for (int i = 0; i < this.openList.size(); i++) {
      if (jNode.getEstimatedValue()
          < this.openList.elementAt(i).getEstimatedValue()) {
        this.openList.insertElementAt(jNode, i);
        return;
      }
    }
    this.openList.addElement(jNode);
  }

  /**
   * BFS Search for getting 3*3 jigsaw's the best solution
   * @return true if completed, false otherwise
   * @throws IOException if any error
   */
  public boolean BFSearch() throws IOException {
    // write to ./BFSearchDialog.txt
    String filePath = "./BFSearchDialog.txt";
    PrintWriter pw = new PrintWriter(new FileWriter(filePath));

    isCompleted = false;
    // Write your code here.
    sortedInsertOpenList(getBeginJNode());

    while (!this.openList.isEmpty()) {
      this.currentJNode = this.openList.elementAt(0);
      if (this.getCurrentJNode().equals(getEndJNode())) {
        isCompleted = true;
        calSolutionPath();
        break;
      }

      this.openList.removeElementAt(0);
      this.closeList.addElement(getCurrentJNode());
      this.searchedNodesNum++;

      pw.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + getCurrentJNode().toString());
      System.out.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + getCurrentJNode().toString());      

      Vector<JigsawNode> adjacent = findFollowJNodes(getCurrentJNode());
      while (!adjacent.isEmpty()) {
        this.openList.addElement(adjacent.elementAt(0));
        adjacent.removeElementAt(0);
      }
    }

    this.printResult(pw);
    pw.close();
    System.out.println("Record into " + filePath);
    return isCompleted;
  }

  /**
   * Heuristic Search for getting random 5*5 jigsaw's solution
   * @return true if completed
   *         false if the number of searchedNodes is over 30000
   * @throws IOException if any error
   */
  public boolean ASearch() throws IOException {
    // write to ASearchDialog.txt
    String filePath = "ASearchDialog.txt";
    PrintWriter pw = new PrintWriter(new FileWriter(filePath));

    int maxNodesNum = 30000;  
    
    Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 

    isCompleted = false;           
    
    this.sortedInsertOpenList(this.beginJNode);

    while (this.openList.isEmpty() != true && searchedNodesNum <= maxNodesNum) {
      
      this.currentJNode = this.openList.elementAt(0);
      if (getCurrentJNode().equals(this.endJNode)){
        isCompleted = true;
        this.calSolutionPath();
        break;
      }
         
      this.openList.removeElementAt(0);
      this.closeList.addElement(getCurrentJNode());
      searchedNodesNum++;
      
      // pw.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + getCurrentJNode().toString());
      // System.out.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + getCurrentJNode().toString());      

      followJNodes = findFollowJNodes(getCurrentJNode());
      while (!followJNodes.isEmpty()) {
        sortedInsertOpenList(followJNodes.elementAt(0));
        followJNodes.removeElementAt(0);
      }
    }
    
    this.printResult(pw);
    pw.close();
    System.out.println("Record into " + filePath);
    return isCompleted;
  }

  /**
   * Caculate the estimated value of jNode, and set the estimatedValue variable of the node.
   * @param jNode the node to estimate
   */
  private void estimateValue(JigsawNode jNode) {
    // number of wrong position
    int s = getNumberOfWrong(jNode)
            + 2 * getDistance(jNode)
            + getNumberOfFollowingWrong(jNode);

    jNode.setEstimatedValue(s);
  }

  private int getNumberOfWrong(JigsawNode jNode) {
    int s = 0;
    int dimension = JigsawNode.getDimension();
    for (int index = 1; index <= dimension * dimension; index++) {
      int temp = jNode.getNodesState()[index];
      if (temp != index && temp != 0) {
        s++;
      }
    }
    return s;
  }

  private int getDistance(JigsawNode jNode) {
    int s = 0;
    int dimension = JigsawNode.getDimension();
    for (int index = 1; index <= dimension * dimension; index++) {
      int temp = jNode.getNodesState()[index];
      if (temp != index && temp != 0) {
        int rowS = (index - 1) / dimension;
        int colS = (index - 1) % dimension;
        int rowE = (temp - 1) / dimension;
        int colE = (temp - 1) % dimension;
        s += Math.abs(rowE - rowS) + Math.abs(colE - colS) - 1;
      }
    }

    return s;
  }

  private int getNumberOfFollowingWrong(JigsawNode jNode) {
    int s = 0;
    int dimension = JigsawNode.getDimension();
    for (int index = 1 ; index < dimension * dimension; index++) {
      if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
        s++;
      }
    }
    return s;
  }

}
