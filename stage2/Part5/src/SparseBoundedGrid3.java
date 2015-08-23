import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.UnboundedGrid;

import java.util.ArrayList;

/**
 * class SparseBoundedGrid3
 * An implementation of BoundedGrid with the help of UnboundedGrid
 *
 * @author Shawn Dai
 */
public class SparseBoundedGrid3<E> extends AbstractGrid<E> {

  private UnboundedGrid<E> occupants;
  private int rows;
  private int cols;

  /**
   * Constructor
   * @param rows
   *             the grid max rows
   * @param cols
   *             the grid max columns
   */
  public SparseBoundedGrid3(int rows, int cols)
  {
    if (rows <= 0)
    {
      throw new IllegalArgumentException("rows <= 0");
    }
    if (cols <= 0)
    {
      throw new IllegalArgumentException("cols <= 0");
    }
    this.rows = rows;
    this.cols = cols;
    // use UnboundedGrid
    this.occupants = new UnboundedGrid<E>();
  }

  @Override
  public int getNumRows()
  {
    return this.rows;
  }

  @Override
  public int getNumCols()
  {
    return this.cols;
  }

  @Override
  public boolean isValid(Location loc)
  {
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
  }

  @Override
  public ArrayList<Location> getOccupiedLocations()
  {
    return this.occupants.getOccupiedLocations();
  }

  @Override
  public E get(Location loc)
  {
    checkForValidLocation(loc);

    return this.occupants.get(loc);
  }

  @Override
  public E put(Location loc, Object obj)
  {
    checkForValidLocation(loc);

    return this.occupants.put(loc, (E) obj);
  }

  @Override
  public E remove(Location loc)
  {
    checkForValidLocation(loc);

    return this.occupants.remove(loc);
  }

  /**
   * Check whether the location is valid or not
   * @param loc
   *            the location to be checked
   * @return true if the loc is valid,
   *         false otherwise.
   */
  private void checkForValidLocation(Location loc)
  {
    if (!isValid(loc))
    {
      throw new IllegalArgumentException("Location " + loc
        + " is not valid");
    }
  }
}
