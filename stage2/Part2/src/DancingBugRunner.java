import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * This class runs a world that contains Dancing bugs. <br />
 */
public final class DancingBugRunner
{
    private DancingBugRunner() {
    }

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int[] a = {3, 2, 3, 3, 2};
        DancingBug alice = new DancingBug(a);
        alice.setColor(Color.ORANGE);
        world.add(new Location(3, 4), alice);
        world.add(new Location(4, 4), new Rock());
        world.show();
    }
}