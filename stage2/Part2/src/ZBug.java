import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces out a square "box" of a given size. <br />
 */
public class ZBug extends Bug
{
  private int steps;
  private int sideLength;
  private int z;

  /**
   * Constructs a Z bug that traces a square of a given side length
   * @param length the side length
   */
  public ZBug(int length)
  {
    steps = 0;
    sideLength = length;
    setDirection(Location.EAST);
    z = 0;
  }

  /**
   * Moves to the next location of the square.
   */
  @Override
  public void act()
  {
    if ( z <= 2 && steps < sideLength && canMove())
    {
      move();
      steps++;
    }
    else
    {
      if (!canMove()) return;
      steps = 0;
      z++;
      if (z == 1) {
        setDirection(Location.SOUTHWEST);
      } else if (z == 2) {
        setDirection(Location.EAST);
      }
    }
  }
}
