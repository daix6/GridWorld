import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SparseBoundedGrid3<E> extends AbstractGrid<E> {

  private Map<Location, E> occupants;
  private int rows;
  private int cols;

  public SparseBoundedGrid3(int rows, int cols)
  {
    if (rows <= 0)
      throw new IllegalArgumentException("rows <= 0");
    if (cols <= 0)
      throw new IllegalArgumentException("cols <= 0");
    this.rows = rows;
    this.cols = cols;
    this.occupants = new TreeMap<Location, E>();
  }

  public int getNumRows()
  {
    return this.rows;
  }

  public int getNumCols()
  {
    return this.cols;
  }

  public boolean isValid(Location loc)
  {
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
  }

  public ArrayList<Location> getOccupiedLocations()
  {
    ArrayList<Location> locs = new ArrayList<Location>();
    for (Location loc : this.occupants.keySet())
    {
      locs.add(loc);
    }
    return locs;
  }

  public E get(Location loc)
  {
    if (!isValid(loc))
    {
      throw new IllegalArgumentException("Location " + loc
        + " is not valid");
    }

    return this.occupants.get(loc);
  }

  public E put(Location loc, Object obj)
  {
    if (!isValid(loc))
    {
      throw new IllegalArgumentException("Location " + loc
        + " is not valid");
    }
    if (obj == null)
    {
     throw new NullPointerException("obj == null");
    }

    return this.occupants.put(loc, (E) obj);
  }

  public E remove(Location loc)
  {
    if (!isValid(loc))
    {
      throw new IllegalArgumentException("Location" + loc
        + " is not valid");
    }
    return this.occupants.remove(loc);
  }

}
