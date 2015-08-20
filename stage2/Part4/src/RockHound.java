import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
import java.awt.Color;

public class RockHound extends Critter {

  /**
   * Constructor
   * @param c critter's color
   */
  public RockHound() {
    setColor(Color.YELLOW);
  }

  /**
   * Constructor
   * @param c critter's color
   */
  public RockHound(Color c) {
    setColor(c);
  }

  /**
   * Removes any rocks in actors from the grid. Moves like a Critter.
   * @param actors actors to be processed
   */
  @Override
  public void processActors(ArrayList<Actor> actors)
  {
    for (Actor a : actors)
    {
      if (a instanceof Rock)
      {
        a.removeSelfFromGrid();
      }
    }
  }
}