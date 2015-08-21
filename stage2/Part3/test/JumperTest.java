// http://junit.org/apidocs/org/junit/Assert.html
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

// import dependecies
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;

import java.awt.Color;

public class JumperTest {
  ActorWorld world;
  Jumper j, j2;

  // init before
  @Before
  public void setUp() throws Exception
  {
    this.world = new ActorWorld(new BoundedGrid(20, 20));
    this.j = new Jumper();
    this.j2 = new Jumper();
  }

  // remove after
  @After
  public void tearDown() throws Exception
  {
  	this.world = null;
  	this.j = null;
  	this.j2 = null;
  }

  @Test
  public void testConstructor()
  {
  	assertEquals(j.getColor(), Color.GREEN);
  	assertEquals(j.getDirection(), Location.NORTH);
  }

  // What will a jumper do if the location in front of it is empty,
  // but the location two cells in front contains a flower or a rock?
  // Turn on Rock, jump on Flower
  @Test
  public void testTwoCellsInFrontOccupied()
  {
  	// test rock
  	Rock rock = new Rock();
  	world.add(new Location(5, 5), j);
  	world.add(new Location(3, 5), rock);
  	j.act();
  	assertEquals("The jumper doesn't jump onto rocks", rock.getLocation().getRow(), 3);
  	assertEquals("The jumper doesn't jump onto rocks", rock.getLocation().getCol(), 5);
  	assertEquals("The jumper will turn", j.getDirection(), Location.NORTHEAST);

  	// test flower
  	Flower flower = new Flower();
  	world.add(new Location(5, 7), flower);
  	j.setDirection(Location.EAST);
  	j.act();
  	assertEquals("The jumper does jump onto flowers", j.getLocation().getRow(), 5);
  	assertEquals("The jumper does jump onto flowers", j.getLocation().getCol(), 7);
  	assertEquals("The jumper will turn", j.getDirection(), Location.EAST);
  	assertNull("The flower will disappear", flower.getGrid());
  }

  // What will a jumper do if the location two cells 
  // in front of the jumper is out of the grid?
  //
  // Turn
  @Test
  public void testInvalidDestination()
  {
    world.add(new Location(1, 1), j);
    j.act();
    assertEquals("The jumper will turn", j.getLocation().getRow(), 1);
    assertEquals("The jumper will turn", j.getLocation().getCol(), 1);
    assertEquals("The jumper's direction", j.getDirection(), Location.NORTHEAST);
  }

  // What will a jumper do if it is facing an edge of the grid?
  //  
  // Turn
  @Test
  public void testEdge()
  {
    world.add(new Location(0, 0), j);
    j.act();
    assertEquals("The jumper will turn", j.getLocation().getRow(), 0);
    assertEquals("The jumper will turn", j.getLocation().getCol(), 0);
    assertEquals("The jumper's direction", j.getDirection(), Location.NORTHEAST);
  }

  // What will a jumper do if another actor (not a flower
  // or a rock) is in the cell that is two cells in front of the jumper?
  // 
  // The jumper will turn
  @Test
  public void testActor()
  {
  	Bug bug = new Bug();
  	world.add(new Location(5, 5), j);
  	world.add(new Location(3, 5), bug);

  	j.act();
    assertEquals("The jumper will turn", j.getLocation().getRow(), 5);
    assertEquals("The jumper will turn", j.getLocation().getCol(), 5);
    assertEquals("The jumper's direction", j.getDirection(), Location.NORTHEAST);
    assertNotNull("The actor should still be there", bug.getGrid());
  }

  // What will a jumper do if it encounters another jumper in its path?
  // 
  // The jumper will jump over the another jumper
  @Test
  public void testJumper()
  {
  	world.add(new Location(6, 6), j);
  	world.add(new Location(5, 6), j2);

  	j.act();

    assertEquals("The jumper will jump over jumper2", j.getLocation().getRow(), 4);
    assertEquals("The jumper will jump over jumper2", j.getLocation().getCol(), 6);
    assertEquals("The jumper's direction stays the same", j.getDirection(), Location.NORTH);


    Jumper j3 = new Jumper();
    Jumper j4 = new Jumper();

    world.add(new Location(10, 10), j3);
    world.add(new Location(8, 10), j4);

    j3.act();

    assertEquals("The jumper will turn", j3.getLocation().getRow(), 10);
    assertEquals("The jumper will turn", j3.getLocation().getCol(), 10);
    assertEquals("The jumper's direction", j3.getDirection(), Location.NORTHEAST);
  }
}