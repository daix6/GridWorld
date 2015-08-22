import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;
import java.awt.Color;

public class BlusterCritter extends Critter {

  private static final double DARKENING_FACTOR = 0.05;

  private int c;

  /**
   * Default constructor
   * @param c courage of critter
   */
  public BlusterCritter(int courage) {
    c = courage;
    setColor(Color.BLACK);
  }

  /**
   * Constructor
   * @param c critter's color
   */
  public BlusterCritter(int courage, Color colour) {
    c = courage;
    setColor(colour);
  }

  /**
   * Gets the actors for processing.
   * Looks at all of the neighbors within two steps of its current location.
   * @return a list of actors that this critter wishes to process.
   */
  public ArrayList<Actor> getActors()
  {
    ArrayList<Actor> actors = new ArrayList<Actor>();
    Grid<Actor> gr = getGrid();

    if (gr == null)
    {
      return actors;
    }

    Location loc = getLocation();

    int rowS = loc.getRow() - 2;
    int rowE = loc.getRow() + 2;
    int colS = loc.getCol() - 2;
    int colE = loc.getCol() + 2;

    for ( int i = rowS; i < rowE; i++)
    {
      for ( int j = colS; j < colE; j++)
      {
        Location tempLoc = new Location(i, j);
        if (gr.isValid(tempLoc))
        {
          Actor tempAc = gr.get(tempLoc);
          if (tempAc instanceof Critter && tempAc != this)
          {
            actors.add(tempAc);
          }
        }
      }
    }
    return actors;
  }

  /**
   * If there are fewer than c critters, 
   * the BlusterCritter's color gets brighter (color values increase).
   * If there are c or more critters, the BlusterCritter's color darkens.
   * @param actors actors to be processed
   */
  @Override
  public void processActors(ArrayList<Actor> actors)
  {
    int n = actors.size();
    if (n < c)
    {
      brighten(getColor());
    } else
    {
      darken(getColor());
    }
    return;
  }

  /**
   * Brighten the color of by increase the original color's RGB.
   * @param c color to be darkened
   */
  public void brighten(Color c)
  {
    int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
    int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
    int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));

    red = red > 255 ? 255 : red;
    green = green > 255 ? 255 : green;
    blue = blue > 255 ? 255 : blue;

    setColor(new Color(red, green, blue));
  }

  /**
   * Darken the color of by decrease the original color's RGB.
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