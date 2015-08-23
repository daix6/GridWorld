import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.util.ArrayList;

/**
 * class UnboundedGrid2
 *
 * The constructor allocates a 16 x 16 array. When a call is made to
 * the put method with a row or column index that is outside the current
 * array bounds, double both array bounds until they are large enough,
 * construct a new square array with those bounds, and place the existing
 * occupants into the new array.
 *
 * @author Shawn Dai
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> {

  private Object[][] grid;
  private int size;
  static public final int SIZE = 16;

  public UnboundedGrid2()
  {
    // the size is set 16 by default
  	this.size = SIZE;
    this.grid = new Object[this.size][this.size];
  }

  @Override
  public int getNumRows()
  {
    return -1;
  }

  @Override
  public int getNumCols()
  {
    return -1;
  }

  @Override
  public boolean isValid(Location loc)
  {
  	return loc.getRow() >= 0 && loc.getCol() >= 0;
  }

  @Override
  public ArrayList<Location> getOccupiedLocations()
  {
  	ArrayList<Location> locs = new ArrayList<Location>();
  	for (int i = 0, lena = this.size; i < lena; i++)
  	{
  	  for (int j = 0, lenb = this.size; j < lenb; j++)
  	  {
  	  	if (grid[i][j] != null)
  	  	{
  	  	  locs.add(new Location(i, j));
  	  	}
  	  }
  	}
  	return locs;
  }

  @Override
  public E get(Location loc)
  {
    checkForValidLocation(loc);

    return checkForOverLocation(loc) 
            ? null
            : (E) grid[loc.getRow()][loc.getCol()];
  }

  @Override
  public E put(Location loc, Object obj)
  {
    checkForValidLocation(loc);

    if (obj == null)
    {
     throw new IllegalArgumentException("obj == null");
    }

    E old;
    // If the loc is out of the grid, expand the current grid
    if (checkForOverLocation(loc))
    {
      // firstly, copy the original grid
      Object[][] big = new Object[this.size * 2][this.size * 2];
      for (int i = 0, lena = this.size; i < lena; i++)
      {
      	for (int j = 0, lenb = this.size; j < lenb; j++)
      	{
      	  big[i][j] = this.grid[i][j];
      	}
      }
      this.grid = big;
      this.size *= 2;
      this.grid[loc.getRow()][loc.getCol()] = obj;
      return null;
    } else
    {
      old = (E) this.grid[loc.getRow()][loc.getCol()];
      this.grid[loc.getRow()][loc.getCol()] = obj;
      return old;
    }
  }

  @Override
  public E remove(Location loc)
  {
    checkForValidLocation(loc);

    if (checkForOverLocation(loc))
    {
      return null;
    }

  	E old = get(loc);
    this.grid[loc.getRow()][loc.getCol()] = null;
    return old;
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

  /**
   * Check whether the location is out of the current grid
   * @param loc
   *            the location to be checked
   * @return true if in the current grid,
   *         false otherwise.
   */
  private boolean checkForOverLocation(Location loc)
  {
    return loc.getRow() >= this.size || loc.getCol() >= this.size;
  }
}
	
