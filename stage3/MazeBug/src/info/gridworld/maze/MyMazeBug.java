package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A MazeBug can find its way in a maze with DFS.
 */
public class MyMazeBug extends Bug {
  private Location next;
  private Location last;
  private  boolean isEnd = false;
  // the ArrayList<Location> records a path from the starting point to current location
  private  Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
  private  Integer stepCount = 0;
  // final message has been shown
  private boolean hasShown = false;

  // the counter for steps, NORTH, EAST, SOUTH, WEST (all the multiple of 90)
  private  int[] countKun = {0, 0, 0, 0};

  /**
   * Constructs a maze bug.
   */
  public MyMazeBug() {
    setColor(Color.GREEN);
  }

  /**
   * Moves to the next location of the maze.
   */
  public void act() {
    if (stepCount == 0)
    {
      last = getLocation();
      ArrayList<Location> arr = new ArrayList<Location>();
      arr.add(last);
      crossLocation.push(arr);
    }

    boolean willMove = canMove();

    if (isEnd) {
      // to show step count when reach the goal    
      if (!hasShown) {
        String msg = stepCount.toString() + " steps";
        JOptionPane.showMessageDialog(null, msg);
        hasShown = true;
      }
    } else if (willMove)
    {
      move();
      // increase step count when move 
    } else
    {
      moveBack();
    }
  }

  /**
   * Find all positions that can be move to.
   * 
   * @param loc
   *            the location to detect.
   * @return List of positions.
   */
  public ArrayList<Location> getValid(Location loc) {
    ArrayList<Location> valid = new ArrayList<Location>();
    Grid<Actor> gr = getGrid();

    if (gr == null)
    {
      return valid;
    }

    int[] dirs = { Location.EAST, Location.SOUTH, Location.WEST, Location.NORTH };
    for (int dir : dirs)
    {
      Location temp = loc.getAdjacentLocation(dir);

      if (!gr.isValid(temp))
      {
        continue;
      }

      Actor nextA = gr.get(temp);
      // Flower is the flag of visited or not
      if (nextA == null || (nextA instanceof Rock && nextA.getColor().equals(Color.RED)))
      {
        valid.add(temp);
      }
    }

    return valid;
  }

  /**
   * Tests whether this bug can move forward into a location that is empty or
   * contains a flower.
   * 
   * @return true if this bug can move.
   */
  public boolean canMove() {
    if (getValid(getLocation()).size() > 0)
    {
      return true;
    }

    return false;
  }

  /**
   * getNext Position randomly
   */
  public void getNext()
  {
    Grid<Actor> gr = getGrid();
    Location current = getLocation();
    ArrayList<Location> locs = getValid(current);

    // If the end is around, move to!
    int maxCount = -1, maxIndex;
    for (Location loc : locs)
    {
      Actor nextA = gr.get(loc);
      maxIndex = current.getDirectionToward(loc) / Location.EAST;

      if (nextA instanceof Rock)
      {
        next = loc;
        last = current;
        isEnd = true;
        return;
      }

      // in the valids locs, find the next location with max times
      if (countKun[maxIndex] > maxCount)
      {
        maxCount = countKun[maxIndex];
        next = loc;
        last = current;
      }
    }
  } 

  /**
   * Moves the bug forward, putting a flower into the location it previously
   * occupied.
   */
  public void move()
  {
    Grid<Actor> gr = getGrid();
    if (gr == null)
    {
      return;
    }

    Location loc = getLocation();
    getNext();

    if (gr.isValid(next)) {
      setDirection(getLocation().getDirectionToward(next));
      moveTo(next);
      stepCount++;

      countKun[last.getDirectionToward(next) / Location.EAST]++;

      // construct the DFS structure for moveBack
      ArrayList<Location> currentNode = new ArrayList<Location>();
      currentNode.addAll(crossLocation.peek());
      currentNode.add(0, next);
      crossLocation.push(currentNode);
    } else
    {
      removeSelfFromGrid();
    }

    // put flower as visited
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
  }

  /**
   * Moves the bug backward, putting a flower into the location it previously
   * occupied.
   */
  public void moveBack()
  {
    Grid<Actor> gr = getGrid();

    if (gr == null || crossLocation.size() <= 1)
    {
      return;
    }

    crossLocation.pop();
    next = crossLocation.peek().get(0);
    last = getLocation();

    if (gr.isValid(next))
    {
      setDirection(getLocation().getDirectionToward(next));
      moveTo(next);
      stepCount++;
    } else
    {
      removeSelfFromGrid();
    }

    countKun[next.getDirectionToward(last) / Location.EAST]--;

    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, last);
  }
}
