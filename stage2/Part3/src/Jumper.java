import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * A <code>Jumper</code> traces out a square "box" of a given size. <br />
 */
public class Jumper extends Actor
{

  /**
   * Constructs a circle bug that traces a square of a given side length
   * @param length the side length
   */
  public Jumper()
  {
  	setColor(Color.GREEN);
  }

  /**
   * Moves the bug forward
   */
  public void move()
  {
  	Grid<Actor> gr = getGrid();
  	if (gr == null)
  	  return;
  	Location loc = getLocation();
  	Location next = loc.getAdjacentLocation(getDirection());
  	Location dest = next.getAdjacentLocation(getDirection());
    if (gr.isValid(dest))
      moveTo(dest);
    else
      removeSelfFromGrid();
  }

  public boolean canMove()
  {
    Grid<Actor> gr = getGrid();
    if (gr == null)
      return false;
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    Location dest = next.getAdjacentLocation(getDirection());
    if (!gr.isValid(dest))
      return false;

    Actor neighbor = gr.get(dest);
    return (neighbor == null) || (neighbor instanceof Flower);
  }

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
      move();
    else
      turn();
  }
}
