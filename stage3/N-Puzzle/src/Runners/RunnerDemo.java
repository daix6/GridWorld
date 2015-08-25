package Runners;

import java.io.IOException;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

public class RunnerDemo {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    if(JigsawNode.getDimension() != 3){
      System.out.println("Wrong dimension of nodes, please change the dimension of JigsawNode to 3");
      return;
    }

    // destNode: {9,1,2,3,4,5,6,7,8,0}
    JigsawNode destNode = new JigsawNode(new int[]{9,1,2,3,4,5,6,7,8,0});      

    // JigsawNode startNode = Jigsaw.scatter(destNode, 1000);
    JigsawNode startNode = new JigsawNode(new int[]{5,1,5,2,7,0,4,6,3,8});
    Jigsaw jigsaw = new Jigsaw(startNode, destNode);

    jigsaw.ASearch();     
  }

}
