import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.util.ArrayList;
import java.awt.Color;

public class KingCrab extends CrabCritter {

  /**
   * Default constructor
   */
  public KingCrab()
  {
    setColor(Color.CYAN);
  }

  /**
   * Constructor
   * @param c kingcrab's color
   */
  public KingCrab(Color c)
  {
    setColor(c);
  }

  public boolean further(Location creature, Location king)
  {
    int x1 = creature.getRow();
    int y1 = creature.getCol();
    int x2 = king.getRow();
    int y2 = king.getCol();
    if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > 2)
    {
      return true;
    }
    return false;
  }

  public boolean canMoveAway(Actor actor)
  {
    if (actor instanceof Rock || actor instanceof Flower)
    {
      return false;
    }
    
    ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(getLocation());
    if (locs.size() == 0)
    {
      return false;
    }
    for (Location loc : locs)
    {
      if (further(loc, getLocation()))
      {
        actor.moveTo(loc);
        return true;
      }
    }
    
    return false;
  }

  /**
   * Causes each actor move one location further away from the KingCrab.
   * If cannot move away, the KingCrab removes it from the grid.
   * @param actors actors to be processed
   */
  @Override
  public void processActors(ArrayList<Actor> actors)
  {
    for (Actor a : actors)
    {
      if (!canMoveAway(a))
      {
        a.removeSelfFromGrid();
      }
    }
  }
}