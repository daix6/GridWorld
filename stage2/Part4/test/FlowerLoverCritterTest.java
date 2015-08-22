import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

// import dependecies
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;

import java.awt.Color;

public class FlowerLoverCritterTest {
  private ActorWorld world;
  private FlowerLoverCritter critter;

  // run before each test
  @Before
  public void setUp()
  {
    world = new ActorWorld(new BoundedGrid(10, 10));
    critter = new FlowerLoverCritter();
    critter.setDirection(Location.NORTH);

    world.add(new Location(6, 6), critter);
  }

  // run after each test
  @After
  public void tearDown()
  {
    world = null;
    critter = null;
  }

  // test the constructor
  @Test
  public void testConstructor()
  {
    assertEquals("Default color should be gray", critter.getColor(), Color.GRAY);
    critter = new FlowerLoverCritter(Color.RED);
    assertEquals("Now it should be red", critter.getColor(), Color.RED);
  }

  // test the critter will leave flower behind it.
  @Test
  public void testLeaveFlower()
  {
    critter.act();
    assertTrue("There should be a flower in (6, 6)", world.getGrid().get(new Location(6, 6)) instanceof Flower);
  }

  // test the critter will eat every neighbor but flowers
  @Test
  public void testEatEverythingButFlower()
  {
    // for bug
    Bug bug = new Bug();
    world.add(new Location(5, 5), bug);
    // for rock
    Rock rock = new Rock();
    world.add(new Location(6, 7), rock);
    // for critter
    Critter c = new Critter();
    world.add(new Location(5, 7), c);

    // for survive
    Bug bug2 = new Bug();
    world.add(new Location(6, 7), bug2);

    critter.act();
    // those actors will disappear from the grid
    assertNull("The bug should be eaten", bug.getGrid());
    assertNull("The rock should be eaten", rock.getGrid());
    assertNull("The c critter should be eaten", c.getGrid());
    // the critter alive
    assertNotNull("The critter should still there", critter.getGrid());
  }

  // test the critter won't eat flower
  @Test
  public void testItDoNotEatFlower()
  {
    Flower flower1 = new Flower();
    Flower flower2 = new Flower();
    Flower flower3 = new Flower();

    world.add(new Location(5, 5), flower1);
    world.add(new Location(5, 6), flower2);
    world.add(new Location(5, 7), flower3);

    critter.act();
    // those flower will still be there
    assertNotNull("The flower1 should still be there", flower1.getGrid());
    assertNotNull("The flower2 should still be there", flower2.getGrid());
    assertNotNull("The flower3 should still be there", flower3.getGrid());
  }

  // test the critter will be removed if it were surrounded by flowers
  @Test
  public void testItWillRemoveItself()
  {
    world.add(new Location(5, 5), new Flower());
    world.add(new Location(5, 6), new Flower());
    world.add(new Location(5, 7), new Flower());
    world.add(new Location(6, 5), new Flower());
    world.add(new Location(6, 7), new Flower());
    world.add(new Location(7, 5), new Flower());
    world.add(new Location(7, 6), new Flower());
    world.add(new Location(7, 7), new Flower());

    critter.act();
    // the critter will disappear
    assertNull("The critter eat itself :p", critter.getGrid());
  }
}