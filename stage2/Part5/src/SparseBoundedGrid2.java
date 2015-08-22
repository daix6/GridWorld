import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {

  private Map<Location, E> occupants;
  private int rows;
  private int cols;

  public SparseBoundedGrid2(int rows, int cols)
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
    this.occupants = new HashMap<Location, E>();
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
    ArrayList<Location> locs = new ArrayList<Location>();
    for (Location loc : this.occupants.keySet())
    {
      locs.add(loc);
    }
    return locs;
  }

  @Override
  public E get(Location loc)
  {
    if (!isValid(loc))
    {
      throw new IllegalArgumentException("Location " + loc
        + " is not valid");
    }

    return this.occupants.get(loc);
  }

  @Override
  public E put(Location loc, Object obj)
  {
    if (!isValid(loc))
    {
      throw new IllegalArgumentException("Location " + loc
        + " is not valid");
    }
    if (obj == null)
    {
     throw new IllegalArgumentException("obj == null");
    }

    return this.occupants.put(loc, (E) obj);
  }

  @Override
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
