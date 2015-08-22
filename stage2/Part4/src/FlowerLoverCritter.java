import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

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

  // process anything but flower
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

  // if n == 0 return null rather than current location
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

  // if loc == null, remove self; otherwise, move and leave flower behind
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