import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

/**
 * The FlowerLoverCritter eats everything but flower.
 * And it will leave flowers after its moving.
 * If there is no way, the critter will remove itself from grid.
 * @author Shawn Dai 
 */
public class FlowerLoverCritter extends Critter {
  /**
   * Default constructor
   */
  public FlowerLoverCritter()
  {
    setColor(Color.GRAY);
  }
  /**
   * Constructor
   * @param c color of critter
   */
  public FlowerLoverCritter(Color c)
  {
    setColor(c);
  }

  @Override
  public void processActors(ArrayList<Actor> actors)
  {
    for (Actor a : actors)
    {
      if (!(a instanceof Flower))
      {
        a.removeSelfFromGrid();
      }
    }
  }

  @Override
  public ArrayList<Location> getMoveLocations()
  {
    ArrayList<Location> all = getGrid().getEmptyAdjacentLocations(getLocation());
    ArrayList<Location> locs = new ArrayList<Location>();

    Grid gr = getGrid();
    for (Location loc : all)
    {
      if (!(gr.get(loc) instanceof Flower))
      {
        locs.add(loc);
      }
    }
    return locs;
  }

  @Override
  public Location selectMoveLocation(ArrayList<Location> locs)
  {
    int n = locs.size();
    if (n == 0)
    {
      return null;
    }
    int r = (int) (Math.random() * n);
    return locs.get(r);
  }

  @Override
  public void makeMove(Location loc)
  {
    Location old = getLocation();
    if (loc == null)
    {
      removeSelfFromGrid();
    }
    else
    {
      moveTo(loc);
      if (!old.equals(getLocation()))
      {
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(getGrid(), old);
      }
    }
  }
}