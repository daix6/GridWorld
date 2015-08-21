import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains bluster critters. <br />
 */
public final class BlusterCritterRunner
{
  private BlusterCritterRunner() {
  }

  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();

    world.add(new BlusterCritter(5));
    world.add(new Location(5, 8), new BlusterCritter(1, Color.PINK));
    world.show();
  }
}