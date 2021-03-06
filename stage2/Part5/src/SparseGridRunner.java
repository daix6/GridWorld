import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Bug;

/**
* This class runs a world with additional sparse bounded grid choices.
*/
public final class SparseGridRunner
{
  private SparseGridRunner()
  {
  }
  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();
    world.addGridClass("SparseBoundedGrid");
    world.addGridClass("SparseBoundedGrid1");
    world.addGridClass("SparseBoundedGrid2");
    world.addGridClass("SparseBoundedGrid3");
    world.addGridClass("UnboundedGrid2");
    world.setGrid(new SparseBoundedGrid3<Actor>(10, 10));
    world.add(new Location(5, 6), new Bug());
    world.add(new Location(4, 3), new Critter());
    world.show();
  }
}