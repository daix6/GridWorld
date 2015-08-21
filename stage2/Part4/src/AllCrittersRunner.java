import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;

import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;

import java.awt.Color;

/**
 * This class runs a world that contains bluster critters. <br />
 */
public final class AllCrittersRunner
{
  private AllCrittersRunner() {
  }

  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld(new BoundedGrid(20, 20));

    world.add(new Location(5, 5), new ChameleonCritter());
    world.add(new Location(10, 10), new ChameleonKid());
    world.add(new Location(5, 10), new RockHound());
    world.add(new Location(10, 5), new BlusterCritter(3));
    world.add(new Location(5, 15), new CrabCritter());
    world.add(new Location(10, 15), new QuickCrab());
    world.add(new Location(15, 15), new KingCrab());

    world.add(new Rock());
    world.add(new Rock());
    world.add(new Rock());
    world.add(new Rock());
    world.add(new Rock());
    world.add(new Rock());
    world.add(new Rock());
    world.add(new Rock());
    world.add(new Flower());
    world.add(new Flower());
    world.add(new Flower());
    world.add(new Flower());
    world.add(new Bug());
    world.add(new Bug());
    world.add(new Bug());
    world.add(new Bug());
    world.add(new Bug());
    world.add(new Bug());
    world.add(new Bug());
    world.add(new Bug());

    world.show();
  }
}