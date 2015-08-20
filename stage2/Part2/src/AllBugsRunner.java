import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;

/**
 * This class runs a world that contains circle bugs. <br />
 */
public final class AllBugsRunner
{
  /**
   * Empty construcor
   */
  private AllBugsRunner() {
  }

  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld(new UnboundedGrid());
    CircleBug alice = new CircleBug(5);
    alice.setColor(Color.ORANGE);
    SpiralBug bob = new SpiralBug(5);
    bob.setColor(Color.BLUE);
    ZBug cat = new ZBug(5);
    cat.setColor(Color.GREEN);
    int[] a = {2, 3, 3, 3};
    DancingBug darker = new DancingBug(a);
    darker.setColor(Color.YELLOW);

    // add all kinds of bugs~
    world.add(new Location(5, 5), alice);
    world.add(new Location(35, 5), bob);
    world.add(new Location(5, 35), cat);
    world.add(new Location(35, 25), darker);
    world.add(new Rock());
    world.add(new Flower());

    world.show();
  }
}