import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

public class QuickCrab extends CrabCritter {

  /**
   * Default constructor
   */
  public QuickCrab()
  {
    setColor(Color.GREEN);
  }

  /**
   * Constructor
   * @param c quickcrab's color
   */
  public QuickCrab(Color c)
  {
  	setColor(c);
  }

  /**
    * @return list of empty locations immediately to the right and to the left
    */
  @Override
  public ArrayList<Location> getMoveLocations()
  {
    ArrayList<Location> locs = new ArrayList<Location>();
    int[] dirs = { Location.LEFT, Location.RIGHT };
    for (Location loc : getLocationsInTwoSpaces(dirs))
    {
      if (getGrid().get(loc) == null)
      {
        locs.add(loc);
      }
    }

    if (locs.size() == 0)
    {
      locs = super.getMoveLocations();
    }

    return locs;
  }

  /**
    * Finds the valid adjacent locations of this critter in different
    * directions.
    * @param directions - an array of directions (which are relative to the
    * current direction)
    * @return a set of valid locations that are two spaces to the QuickCrab's
    * right or left.
    */
  public ArrayList<Location> getLocationsInTwoSpaces(int[] directions)
  {
    ArrayList<Location> locs = new ArrayList<Location>();
    Grid gr = getGrid();
    Location loc = getLocation();

    for (int d : directions)
    {
      Location next = loc.getAdjacentLocation(getDirection() + d);
      if (gr.isValid(next) && gr.get(next) == null)
      {
        Location dest = next.getAdjacentLocation(getDirection() + d);
        if (gr.isValid(dest))
        {
          locs.add(dest);
        }
      }
    }
    return locs;
  }

}
  
