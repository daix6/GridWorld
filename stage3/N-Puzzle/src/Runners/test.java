package Runners;

import java.io.IOException;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

public class test {
  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    
    if (JigsawNode.getDimension() != 5) {
      System.out.println("Wrong dimension of nodes, please change the dimension of JigsawNode to 5");
      return;
    }
    
    int[] a = new int[50];
    JigsawNode destNode = new JigsawNode(new int[]{25,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,0});      
    JigsawNode startNode;
    for (int i = 0; i < 50; i++) {
      startNode = Jigsaw.scatter(destNode, 1000);
      Jigsaw jigsaw = new Jigsaw(startNode, destNode);
      jigsaw.ASearch();
      a[i] = jigsaw.getSearchedNodesNum();
    }

    int averge = 0;
    for (int i = 0; i < 50; i++) {
      averge += a[i];
    }
    System.out.println("Average: " + averge/50);
    
  }

}
