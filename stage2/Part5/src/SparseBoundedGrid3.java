import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.UnboundedGrid;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SparseBoundedGrid3<E> extends AbstractGrid<E> {

  private UnboundedGrid<E> occupants;
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
