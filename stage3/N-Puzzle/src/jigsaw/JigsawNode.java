package jigsaw;

import java.io.IOException;

/** class JigsawNode
 * The abstract data structure of the Jigsaw, which defines the state and operations of the Jigsaw.
 * @author Shawn Dai
 */
public class JigsawNode {
  private static final int dimension = 3;
  // 0th stores the position of emptyGrid
  // others store the value of corresponding position
  private int[] nodesState;
  // path's depth
  private int nodeDepth;
  private JigsawNode parent;
  // estimated value
  private int estimatedValue;

  /** Constructor
   *  @param data the array stores the state of nodes 
   */
  public JigsawNode(int[] data) {
    if(data.length == this.dimension*dimension+1){
      this.nodesState = new int[data.length];
      for (int i = 0; i < this.nodesState.length; i++)
        this.nodesState[i] = data[i];
      this.nodeDepth = 0;
      this.parent = null;
      this.estimatedValue = 0;
    } else
      System.out.println("传入参数错误：当前的节点维数为3.请传入长度为" + (dimension * dimension + 1)
          + "的节点状态数组，或者调整Jigsaw类中的节点维数dimension");
  }

  /** Constructor
   *  create a copy of given parametor
   *  @param jNode the JigsawNode to copy
   */
  public JigsawNode(JigsawNode jNode) {
    this.nodesState = new int[dimension * dimension + 1];
    this.nodesState = (int[]) jNode.nodesState.clone();
    this.nodeDepth = jNode.nodeDepth;
    this.parent = jNode.parent;
    this.estimatedValue = jNode.estimatedValue;
  }

  /**
   * get dimension
   * @return get current dimension
   */
  public static int getDimension() {
    return dimension;
  }

  /**
   * get nodesState
   * @return get current nodesState
   */
  public int[] getNodesState() {
    return nodesState;
  }

  /**
   * get node's depth
   * @return get current nodeDepth
   */
  public int getNodeDepth() {
    return nodeDepth;
  }

  /**
   * get node's parent
   * @return get current parent
   */
  public JigsawNode getParent() {
    return parent;
  }

  /**
   * get node's estimated value
   * @return get current estimatedValue
   */
  public int getEstimatedValue() {
    return estimatedValue;
  }

  /**
   * set node's estimated value
   * @return get current estimated value
   */
  public void setEstimatedValue(int estimatedValue) {
    this.estimatedValue = estimatedValue;
  }

  /**
   * initialize the node's estimatedValue, nodeDepth, parent
   */
  public void setInitial() {
    this.estimatedValue = 0;
    this.nodeDepth = 0;
    this.parent = null;
  }

  /**
   * compare two nodes' state
   * @param  obj  a instance of JigsawNode to compare
   * @return true if the same, false otherwise
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof JigsawNode)) {
      return false;
    }

    for (int i = 0; i < this.nodesState.length; i++) {
      if (this.nodesState[i] != ((JigsawNode) obj).nodesState[i]) {
        return false;
      }
    }

    return true;
  }

  /**
   * toString for print, display as 1-dimension's array
   * @return String the result string
   */
  public String toString() {
    String str = new String();
    str += "{" + this.nodesState[0];
    for(int index = 1; index <= dimension * dimension; index++)
      str += "," + this.nodesState[index];
    str += "}";
    return str;
  }
  
  /**
   * toString for print, display as matrix
   * @return String the result string
   */
  public String toMatrixString() {
    String str = new String();
    for (int x = 1,index = 1; x <= dimension; x++) {
      for (int y = 1; y <= dimension; y++,index++){
        str += this.nodesState[index] + "  ";
      }
      str += "\n";
    }
    return str;
  }

  /**
   * detect all the positions that the emptyGrid can move to
   * @return int[] array with 4 elements, which represents canMove (1) or not (0).
   *               All position represents up, down, left, right.
   */
  public int[] canMove() {
    int[] movable = new int[] { 0, 0, 0, 0 };
    if (canMoveEmptyUp()) {
      movable[0] = 1;
    }
    if (canMoveEmptyDown) {
      movable[1] = 1;
    }
    if (canMoveEmptyLeft) {
      movable[2] = 1;
    }
    if (canMoveEmptyRight) {
      movable[3] = 1;
    }
    return movable;
  }

  /**
   * detect that whether the emptyGrid can move toward up
   * @return true if can, false otherwise.
   */
  public boolean canMoveEmptyUp() {
    return (this.nodesState[0] > dimension && this.nodesState[0] <= dimension
        * dimension);
  }

  /**
   * detect that whether the emptyGrid can move toward down
   * @return true if can, false otherwise.
   */
  public boolean canMoveEmptyDown() {
    return (this.nodesState[0] >= 1 && this.nodesState[0] <= dimension
        * (dimension - 1));
  }

  /**
   * detect that whether the emptyGrid can move toward left
   * @return true if can, false otherwise.
   */
  public boolean canMoveEmptyLeft() {
    return (this.nodesState[0] % dimension != 1);
  }

  /**
   * detect that whether the emptyGrid can move toward right
   * @return true if can, false otherwise.
   */
  public boolean canMoveEmptyRight() {
    return (this.nodesState[0] % dimension != 0);
  }

  /**
   * move the emptyGrid to one direction
   * @param direction direction to move to
   * @return true if success, false otherwise
   */
  public boolean move(int direction) {
    switch (direction) {
      case 0: 
        return this.moveEmptyUp();
      case 1:
        return this.moveEmptyDown();
      case 2:
        return this.moveEmptyLeft();
      case 3:
        return this.moveEmptyRight();
      default:
        return false;
    }
  }

  /**
   * Move the emptyGrid to up
   * @return true if success, false otherwise
   */
  public boolean moveEmptyUp() {
    int emptyPos = this.nodesState[0];
    int emptyValue = this.nodesState[emptyPos];
    if (canMoveEmptyUp()) {
      this.parent = new JigsawNode(this);
      this.nodeDepth++;
      
      this.nodesState[emptyPos] = this.nodesState[emptyPos - dimension];
      this.nodesState[emptyPos - dimension] = emptyValue;
      this.nodesState[0] = emptyPos - dimension;

      return true;
    }
    return false;
  }

  /**
   * Move the emptyGrid to down
   * @return true if success, false otherwise
   */
  public boolean moveEmptyDown() {
    int emptyPos = this.nodesState[0];
    int emptyValue = this.nodesState[emptyPos];
    if (canMoveEmptyDown()) {
      this.parent = new JigsawNode(this);
      this.nodeDepth++;

      this.nodesState[emptyPos] = this.nodesState[emptyPos + dimension];
      this.nodesState[emptyPos + dimension] = emptyValue;
      this.nodesState[0] = emptyPos + dimension;
      return true;
    }
    return false;
  }

  /**
   * Move the emptyGrid to left
   * @return true if success, false otherwise
   */
  public boolean moveEmptyLeft() {
    int emptyPos = this.nodesState[0];
    int emptyValue = this.nodesState[emptyPos];
    if (canMoveEmptyLeft()) {
      this.parent = new JigsawNode(this);
      this.nodeDepth++;

      this.nodesState[emptyPos] = this.nodesState[emptyPos - 1];
      this.nodesState[emptyPos - 1] = emptyValue;
      this.nodesState[0] = emptyPos - 1;
      return true;
    }
    return false;
  }

  /**
   * Move the emptyGrid to right
   * @return true if success, false otherwise
   */
  public boolean moveEmptyRight() {
    int emptyPos = this.nodesState[0];
    int emptyValue = this.nodesState[emptyPos];
    if (canMoveEmptyRight()) {
      this.parent = new JigsawNode(this);
      this.nodeDepth++;

      this.nodesState[emptyPos] = this.nodesState[emptyPos + 1];
      this.nodesState[emptyPos + 1] = emptyValue;
      this.nodesState[0] = emptyPos + 1;
      return true;
    }
    return false;
  }

}
