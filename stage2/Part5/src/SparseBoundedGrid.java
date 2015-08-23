import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.util.ArrayList;

/**
 * class SparseBoundedGrid
 * An implementation of BoundedGrid with the help of raw SparseGridNode.
 * 
 * @author Shawn Dai
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E> {

  private SparseGridNode[] occupants;
  private int rows;
  private int cols;
  
  /**
   * Constructor
   * @param rows
   *             the grid max rows
   * @param cols
   *             the grid max columns
   */
  public SparseBoundedGrid(int rows, int cols)
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
    this.occupants = new SparseGridNode[rows];
  }

  // return the rows of the grid
  @Override
  public int getNumRows()
  {
    return this.rows;
  }

  // return the cols of grid
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
    ArrayList<Location> locs = new ArrayList<Location>();
    for (int i = 0; i < getNumRows(); i++)
    {
      SparseGridNode start = occupants[i];
      while (start != null)
      {
        locs.add(new Location(i, start.getCol()));
        start = start.getNext();
      }
    }
    return locs;
  }

  @Override
  public E get(Location loc)
  {
    checkForValidLocation(loc);

    SparseGridNode start = occupants[loc.getRow()];

    while (start != null)
    {
      if (start.getCol() == loc.getCol())
      {
        return (E) start.getOccupant();
      }
      start = start.getNext();
    }

    return null;
  }

  @Override
  public E put(Location loc, Object obj)
  {
    checkForValidLocation(loc);

    if (obj == null)
    {
     throw new IllegalArgumentException("obj == null");
    }

    E old = remove(loc);

    SparseGridNode start = occupants[loc.getRow()];
    SparseGridNode node = new SparseGridNode(obj, loc.getCol(), start);
    occupants[loc.getRow()] = node;

    return old;
  }

  @Override
  public E remove(Location loc)
  {
    checkForValidLocation(loc);

  if (occupants[loc.getRow()] == null)
    {
      return null;
    }

    SparseGridNode pre = null;
    SparseGridNode start = occupants[loc.getRow()];
    E old;

    while (start != null)
    {
      if (start.getCol() == loc.getCol())
      {
        old = (E) start.getOccupant();
        if (pre == null) {
          occupants[loc.getRow()] = start.getNext();
        } else
        {
          pre.setNext(start.getNext());
        }
        return old;
      }
      pre = start;
      start = start.getNext();
    }
    return null;
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
