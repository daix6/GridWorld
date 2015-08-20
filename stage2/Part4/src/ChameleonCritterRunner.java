import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ChameleonCritterRunner
{
  private ChameleonCritterRunner() {
  }

  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();

    world.add(new Location(4, 4), new ChameleonCritter());
    world.add(new Location(5, 8), new ChameleonCritter(Color.YELLOW));
    world.show();
  }
}