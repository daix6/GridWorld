import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;

import java.awt.Color;

/**
 * A <code>Jumper</code> traces out a square "box" of a given size. <br />
 */
public class Jumper extends Actor
{

  /**
   * Constructs a jumper
   */
  public Jumper()
  {
    setColor(Color.GREEN);
    setDirection(Location.NORTH);
  }

  /**
   * Constructs a jumper define custom color
   * @param c the jumper's color
   */
  public Jumper(Color c)
  {
    setColor(c);
    setDirection(Location.NORTH);
  }

  /**
   * Moves the bug forward
   */
  public void move()
  {
    Grid<Actor> gr = getGrid();
    if (gr == null)
    {
      return;
    }
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    Location dest = next.getAdjacentLocation(getDirection());
    
    moveTo(dest);
  }

  /**
   * Tests whether this bug can move forward into a location that is empty or
   * contains a flower.
   * @return true if this bug can move.
   */
  public boolean canMove()
  {
    Grid<Actor> gr = getGrid();
    if (gr == null)
    {
      return false;
    }
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    Location dest = next.getAdjacentLocation(getDirection());
    if (!gr.isValid(dest))
    {
      return false;
    }

    Actor neighbor = gr.get(dest);
    return (neighbor == null) || (neighbor instanceof Flower);
  }

  /**
   * Turns the bug 45 degrees to the right without changing its location.
   */
  public void turn() {
    setDirection(getDirection() + Location.HALF_RIGHT);
  }

  /**
   * Moves to the next location of the square.
   */
  @Override
  public void act()
  {
    if (canMove())
    {
      move();
    }
    else
    {
      turn();
    }
  }
}
