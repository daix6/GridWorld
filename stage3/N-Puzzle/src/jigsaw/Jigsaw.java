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

    // Write your code here.
    this.openList.add(getBeginJNode());

    while (this.openList.size() > 0) {
      JigsawNode current = this.openList.firstElement();
      if (current.equals(getEndJNode())) {
        isCompleted = true;
        break;
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
    
    // 访问节点数大于30000个则认为搜索失败
    int maxNodesNum = 30000;  
    
    // 用以存放某一节点的邻接节点
    Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
    
    // 重置求解完成标记为false
    isCompleted = false;           
    
    // (1)将起始节点放入openList中
    this.sortedInsertOpenList(this.beginJNode);
    
    // (2) 如果openList为空，或者访问节点数大于maxNodesNum个，则搜索失败，问题无解;否则循环直到求解成功
    while (this.openList.isEmpty() != true && searchedNodesNum <= maxNodesNum) {
      
      // (2-1)访问openList的第一个节点N，置为当前节点currentJNode
      //      若currentJNode为目标节点，则搜索成功，设置完成标记isCompleted为true，计算解路径，退出。
      this.currentJNode = this.openList.elementAt(0);
      if (this.currentJNode.equals(this.endJNode)){
        isCompleted = true;
        this.calSolutionPath();
        break;
      }
      
      // (2-2)从openList中删除节点N,并将其放入closeList中，表示以访问节点      
      this.openList.removeElementAt(0);
      this.closeList.addElement(this.currentJNode);
      searchedNodesNum++;
      
        // 记录并显示搜索过程
        pw.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + this.currentJNode.toString());
        System.out.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + this.currentJNode.toString());      

      // (2-3)寻找所有与currentJNode邻接且未曾被访问的节点，将它们按代价估值从小到大排序插入openList中
      followJNodes = this.findFollowJNodes(this.currentJNode);
      while (!followJNodes.isEmpty()) {
        this.sortedInsertOpenList(followJNodes.elementAt(0));
        followJNodes.removeElementAt(0);
      }
    }
    
    this.printResult(pw);  // 记录搜索结果
    pw.close();       // 关闭输出文件
    System.out.println("Record into " + filePath);
    return isCompleted;
  }
  
  /**（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)=s(n)。
   * s(n)代表后续节点不正确的数码个数
   * @param jNode - 要计算代价估计值的节点；此函数会改变该节点的estimatedValue属性值。
   */
  private void estimateValue(JigsawNode jNode) {
    int s = 0; // 后续节点不正确的数码个数
    int dimension = JigsawNode.getDimension();
    for(int index =1 ; index<dimension*dimension; index++){
      if(jNode.getNodesState()[index]+1!=jNode.getNodesState()[index+1])
        s++;
    }
    jNode.setEstimatedValue(s);
  }

}
