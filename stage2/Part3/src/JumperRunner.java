import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * This class runs a world that contains Jumper. <br />
 */
public final class JumperRunner
{
    private JumperRunner() {
    }

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new BoundedGrid(55, 55));
        Jumper alice = new Jumper();
        alice.setColor(Color.ORANGE);
        Jumper bob = new Jumper();
        Jumper cat = new Jumper();
        cat.setColor(Color.BLUE);

        world.add(new Location(10, 10), alice);
        world.add(new Location(9, 10), new Rock());
        world.add(new Location(11, 10), new Rock());
        world.add(new Location(10, 9), new Rock());
        world.add(new Location(10, 11), new Rock());
        world.add(new Location(9, 9), new Rock());
        world.add(new Location(11, 11), new Rock());
        world.add(new Location(9, 11), new Rock());
        world.add(new Location(11, 9), new Rock());

        world.add(new Location(1, 1), bob);

        world.add(new Location(30, 30), cat);
        world.add(new Location(29, 30), new Flower());
        world.add(new Location(31, 30), new Flower());
        world.add(new Location(30, 29), new Flower());
        world.add(new Location(30, 31), new Flower());

        world.show();
    }
}