import java.awt.*;
import java.awt.event.*;

public class CaculatorModel extends Frame {

  private TextField numberA, numberB;
  private Label empty, opEqual, result;
  private Button opPlus, opMinus, opMutiple, opDivide;
  private Button btnOK;

  private double a, b;
  private String op;

  public CaculatorModel() {

    setLayout(new GridLayout(2, 5, 3, 3));

    numberA = new TextField();
    add(numberA);

    empty = new Label("");
    add(empty);

    numberB = new TextField();
    add(numberB);

    opEqual = new Label("=", Label.CENTER);
    add(opEqual);

    result = new Label();
    add(result);

    opPlus = new Button("+");
    add(opPlus);

    opMinus = new Button("-");
    add(opMinus);

    opMutiple = new Button("*");
    add(opMutiple);

    opDivide = new Button("/");
    add(opDivide);

    btnOK = new Button("OK");
    add(btnOK);

    OpListener listener = new OpListener();
    opPlus.addActionListener(listener);
    opMinus.addActionListener(listener);
    opMutiple.addActionListener(listener);
    opDivide.addActionListener(listener);
    btnOK.addActionListener(listener);

    setTitle("Easy Caculator");
    setSize(268, 109);

  }

  private class OpListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String btnLabel = e.getActionCommand();

      if (btnLabel.equals("OK")) {

        a = Double.parseDouble(numberA.getText());
        b = Double.parseDouble(numberB.getText());
        result.setText("" + caculate(a, op, b));
        result.setAlignment(Label.CENTER);

      } else {

        op = btnLabel;
        empty.setText(op);
        empty.setAlignment(Label.CENTER);

      }
    }

    private double caculate(double a, String op, double b) {

      if (op.equals("+")) {
        return a + b;
      } else if (op.equals("-")) {
        return a - b;
      } else if (op.equals("*")) {
        return a * b;
      } else if (op.equals("/")) {
        return a / b;
      } else {
        return Double.NaN;
      }
    }

  }
  
}