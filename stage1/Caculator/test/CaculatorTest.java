import caculator.CaculatorModel;

import static org.junit.Assert.*;
import org.junit.Test;

public class CaculatorTest {
  @Test
  public void caculateTest() {
    CaculatorModel cm = new CaculatorModel();
    double delta = 0.5;
    assertEquals(cm.caculate(1.0, "+", 1.0), 1.0 + 1.0, delta);
    assertEquals(cm.caculate(1.0, "-", 1.0), 1.0 - 1.0, delta);
    assertEquals(cm.caculate(1.0, "*", 1.0), 1.0 * 1.0, delta);
    assertEquals(cm.caculate(1.0, "/", 1.0), 1.0 / 1.0, delta);
  }

}
