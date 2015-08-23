import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * class SparseBoundedGrid1
 * An implementation of BoundedGrid with the help of LinkedList
 * 
 * @author Shawn Dai
 */
public class SparseBoundedGrid1<E> extends AbstractGrid<E> {

  private ArrayList<LinkedList<OccupantInCol>> occupants;
  private int rows;
  private int cols;

  /**
   * Constructor
   * @param rows
   *             the grid max rows
   * @param cols
   *             the grid max columns
   */
  public SparseBoundedGrid1(int rows, int cols)
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
    // LinkedList<OccupantInCol> occupants = new LinkedList<OccupantInCol>[5]
    // cause error: error: generic array creation
    this.occupants = new ArrayList<LinkedList<OccupantInCol>>();
    for (int i = 0; i < rows; i++)
    {
      this.occupants.add(new LinkedList<OccupantInCol>());
    }
  }

  @Override
  public boolean isValid(Location loc)
  {
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
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
  public ArrayList<Location> getOccupiedLocations()
  {
    ArrayList<Location> locs = new ArrayList<Location>();
    for (int i = 0, length = getNumRows(); i < length; i++)
    {
      LinkedList<OccupantInCol> row = occupants.get(i);
      for (int j = 0, size = row.size(); j < size; j++)
      {
      	locs.add(new Location(i, row.get(j).getCol()));
      }
    }
    return locs;
  }

  @Override
  public E get(Location loc)
  {
    checkForValidLocation(loc);

    int row = loc.getRow();
    int col = loc.getCol();

    LinkedList<OccupantInCol> list = occupants.get(row);
    for (int j = 0, size = list.size(); j < size; j++)
    {
      if (list.get(j).getCol() == col)
      {
        return (E) list.get(j).getOccupant();
      }
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
    occupants.get(loc.getRow()).addFirst(new OccupantInCol(obj, loc.getCol()));
    return old;
  }

  @Override
  public E remove(Location loc)
  {
    checkForValidLocation(loc);
    int col = loc.getCol();

    LinkedList<OccupantInCol> list = occupants.get(loc.getRow());
    for (int j = 0, size = list.size(); j < size; j++)
    {
      if (list.get(j).getCol() == col)
      {
        return (E) (list.remove(j).getOccupant());
      }
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
