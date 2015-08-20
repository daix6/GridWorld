import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> traces out a square "box" of a given size. <br />
 */
public class DancingBug extends Bug
{
  private int steps;
  private int[] turns;

  /**
   * Constructs a Z bug that traces a square of a given side length
   * @param length the side length
   */
  public DancingBug(int[] turnsArray)
  {
    steps = 0;
    turns = turnsArray.clone();
  }

  /**
   * Turns the bug times * 45 degrees to the right without changing its location.
   * @param times the times the bug turns
   */
  public void turn(int times)
  {
    for (int i = 0; i < times; i++) {
      super.turn();
    }
  }

  /**
   * Moves to the next location of the square.
   */
  @Override
  public void act()
  {
    if (canMove()) {
      turn(turns[steps]);
      steps = (steps + 1) % turns.length;
    }
    super.act();
  }
}
