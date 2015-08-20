import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 */
public class ChameleonCritter extends Critter
{
  private static final double DARKENING_FACTOR = 0.05;

  /**
   * Default constructor
   */
  public ChameleonCritter()
  {
    setColor(Color.PINK);
  }

  /**
   * Constructor
   * @param c critter's color
   */
  public ChameleonCritter(Color c)
  {
    setColor(c);
  }

  /**
   * Randomly selects a neighbor and changes this critter's color to be the
   * same as that neighbor's. If there are no neighbors, the color of the
   * ChameleonCritter will darken.
   * @param actors actors to be processed
   */
  @Override
  public void processActors(ArrayList<Actor> actors)
  {
    int n = actors.size();
    if (n == 0)
    {
      darken(getColor());
      return;
    }
    int r = (int) (Math.random() * n);

    Actor other = actors.get(r);
    setColor(other.getColor());
  }

  /**
   * Turns towards the new location as it moves.
   */
  @Override
  public void makeMove(Location loc)
  {
    setDirection(getLocation().getDirectionToward(loc));
    super.makeMove(loc);
  }

  /**
   * Darken the color of ChameleonCritter by decrease the original color's
   * RGB.
   * @param c color to be darkened
   */
  public void darken(Color c)
  {
    int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
    int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
    int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

    setColor(new Color(red, green, blue));
  }

}
