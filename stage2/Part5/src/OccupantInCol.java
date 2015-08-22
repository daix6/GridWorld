public class OccupantInCol {
  private Object occupant;
  private int col;

  public OccupantInCol(Object obj, int col)
  {
    this.occupant = obj;
    this.col = col;
  }

  public void setOccupant(Object occupant)
  {
    this.occupant = occupant;
  }

  public Object getOccupant()
  {
    return this.occupant;
  }

  public void setCol(int col)
  {
    this.col = col;
  }

  public int getCol()
  {
    return this.col;
  }

}