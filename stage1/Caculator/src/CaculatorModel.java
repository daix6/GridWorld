import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CaculatorModel extends Frame {

  private JTextField numberA, numberB;
  private JLabel empty, opEqual, result;
  private JButton opPlus, opMinus, opMutiple, opDivide;
  private JButton btnOK;

  private double a, b;
  private String op;

  public CaculatorModel() {

    setLayout(new GridLayout(2, 5, 3, 3));

    Border border = LineBorder.createGrayLineBorder();

    numberA = new JTextField();
    numberA.setHorizontalAlignment(JTextField.CENTER);
    add(numberA);

    empty = new JLabel("", JLabel.CENTER);
    empty.setBorder(border);
    add(empty);

    numberB = new JTextField();
    numberB.setHorizontalAlignment(JTextField.CENTER);
    add(numberB);

    opEqual = new JLabel("=", JLabel.CENTER);
    opEqual.setBorder(border);
    add(opEqual);

    result = new JLabel("", JLabel.CENTER);
    result.setBorder(border);
    add(result);

    opPlus = new JButton("+");
    add(opPlus);

    opMinus = new JButton("-");
    add(opMinus);

    opMutiple = new JButton("*");
    add(opMutiple);

    opDivide = new JButton("/");
    add(opDivide);

    btnOK = new JButton("OK");
    add(btnOK);

    OpListener listener = new OpListener();
    opPlus.addActionListener(listener);
    opMinus.addActionListener(listener);
    opMutiple.addActionListener(listener);
    opDivide.addActionListener(listener);
    btnOK.addActionListener(listener);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    setTitle("Easy Caculator");
    setSize(298, 129);

  }

  private class OpListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String btnLabel = e.getActionCommand();

      if (btnLabel.equals("OK")) {

        a = Double.parseDouble(numberA.getText());
        b = Double.parseDouble(numberB.getText());
        result.setText("" + caculate(a, op, b));
        result.setHorizontalAlignment(JLabel.CENTER);

      } else {

        op = btnLabel;
        empty.setText(op);
        empty.setHorizontalAlignment(JLabel.CENTER);

      }
    }
  }

  public static double caculate(double a, String op, double b) {
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