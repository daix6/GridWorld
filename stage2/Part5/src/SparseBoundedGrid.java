import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {

  private SparseGridNode[] occupants;
  private int rows;
  private int cols;
  
  public SparseBoundedGrid(int rows, int cols)
  {
    if (rows <= 0)
      throw new IllegalArgumentException("rows <= 0");
    if (cols <= 0)
      throw new IllegalArgumentException("cols <= 0");
    this.rows = rows;
    this.cols = cols;
  }

  public int getRowsNum()
  {
  	return this.rows;
  }

  public int getColsNum()
  {
  	return this.cols;
  }

  public boolean isValid(Location loc)
  {
    return 0 <= loc.getRow() && loc.getRow() <= this.rows
            && 0 <= loc.getCol() && loc.getCol() <= this.cols;
  }

  public ArrayList<Location> getOccupiedLocations()
  {
    for (int i = 0; i < rows; i++)
    {

    }
  }

  public E get(Location loc)
  {
  	int row = loc.getRow();
  	int col = loc.getCol();
  	if (!isValid(loc))
  	{
      throw new IllegalArgumentException("Location " + loc
        + " is not valid");
    }
    
    int start = this.occupants[row];
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

  }

  public E remove(Location loc)
  {
  	if (!isValid(loc))
  	{
  	  throw new IllegalArgumentException("Location " + loc
        + " is not valid");
  	}

  	E r
  }

}
