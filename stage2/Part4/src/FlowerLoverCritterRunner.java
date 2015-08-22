import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains FlowerLover critters. <br />
 */
public final class FlowerLoverCritterRunner
{
  private FlowerLoverCritterRunner() {
  }

  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();

    world.add(new Location(4, 4), new FlowerLoverCritter());
    world.add(new Flower());
    world.add(new Flower());
    world.add(new Flower());
    world.add(new Flower());
    world.add(new Flower());
    world.add(new Rock());
    world.add(new Rock());
    world.show();
  }
}