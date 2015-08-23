package info.gridworld.maze;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.actor.Rock;
import java.awt.Color;

/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class MyMazeBugRunner
{
  private MyMazeBugRunner()
  {
  }

  public static void main(String[] args)
  {
    // UnboundedGrid ugr = new UnboundedGrid();
    ActorWorld world = new ActorWorld(); 
    world.add(new Location(5, 5), new MyMazeBug());
    world.add(new Location(1,1),new Rock());
    world.show();
  }
}
