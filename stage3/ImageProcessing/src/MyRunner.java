import imagereader.Runner;

public final class MyRunner {
  private MyRunner()
  {
  }

  public static void main(String[] args) {
    Runner.run(new MyIImageIO(), new MyIImageProcessor());
    return;
  }
}