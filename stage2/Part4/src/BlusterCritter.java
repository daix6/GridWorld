import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.awt.Color;

public class BlusterCritter extends Critter {
  private int courage;

  /**
   * Default constructor
   * @param c courage of critter
   */
  public BlusterCritter(int c) {
  	courage = c;
    setColor(Color.YELLOW);
  }

  /**
   * Constructor
   * @param c critter's color
   */
  public BlusterCritter(int c, Color colour) {
  	courage = c;
    setColor(colour);
  }

  /**
   * Gets the actors for processing.
   * Looks at all of the neighbors within two steps of its current location.
   * @return a list of actors that this critter wishes to process.
   */
  public ArrayList<Actor> getActors()
  {
    return getGrid().getNeighbors(getLocation());
  }

}