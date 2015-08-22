import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Bug;

/**
* This class runs a world with additional sparse bounded grid choices.
*/
public class SparseGridRunner
{
  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();
    world.addGridClass("SparseBoundedGrid");
    world.addGridClass("SparseBoundedGrid1");
    world.setGrid(new SparseBoundedGrid1<Actor>(5, 5));
    world.add(new Location(2, 3), new Bug());
    world.add(new Location(2, 2), new Critter());
    world.show();
  }
}