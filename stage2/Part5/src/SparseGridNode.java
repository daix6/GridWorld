public class SparseGridNode {
  private Object occupant;
  private int col;
  private SparseGridNode next;

  public SparseGridNode(Object occupant, int col, SparseGridNode next)
  {
  	this.occupant = occupant;
  	this.col = col;
  	this.next = null;
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

  public void setNext(SparseGridNode next)
  {
  	this.next = next;
  }

  public SparseGridNode getNext()
  {
  	return this.next;
  }

}