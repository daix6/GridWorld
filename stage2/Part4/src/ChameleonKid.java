import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>ChameleonKid</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 */
public class ChameleonKid extends ChameleonCritter
{

  /**
   * Default constructor
   */
  public ChameleonKid()
  {
    setColor(Color.BLUE);
  }

  /**
   * Constructor
   * @param c critter's color
   */
  public ChameleonKid(Color c)
  {
    setColor(c);
  }

  /**
   * Gets all the actors immediately in front or behind the critter for processing.
   * @return a list of actors that this critter wishes to process.
   */
  @Override
  public ArrayList<Actor> getActors()
  {
    ArrayList<Actor> actors = new ArrayList<Actor>();
    int [] dirs = { Location.AHEAD, Location.HALF_CIRCLE };

    for (Location loc : getLocationsInDirections(dirs))
    {
      Actor a = getGrid().get(loc);
      if (a != null)
      {
        actors.add(a);
      }
    }

    return actors;
  }

  /**
   * Finds the valid adjacent locations of this critter in different
   * directions.
   * @param directions - an array of directions (which are relative to the
   * current direction)
   * @return a set of valid locations that are neighbors of the current
   * location in the given directions
   */
  public ArrayList<Location> getLocationsInDirections(int[] directions)
  {
    ArrayList<Location> locs = new ArrayList<Location>();
    Grid gr = getGrid();
    Location loc = getLocation();
  
    for (int d : directions)
    {
      Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
      if (gr.isValid(neighborLoc))
      {
        locs.add(neighborLoc);
      }
    }
    return locs;
  }  

}
