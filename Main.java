import java.util.*;
import java.awt.*;
import javax.swing.*;
import Build.*;
import AST.*;

public class Main extends JFrame{
    JPanel panel;
    JLabel output;
    JTextField calcEntry;
    JTextField varEntry;
    JButton calcButton;

    public Main(){
        super("Calculator");
        setLocationRelativeTo(null);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));
        panel.setBackground(Color.LIGHT_GRAY);

        calcEntry = new JTextField(15);
        calcEntry.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));

        varEntry = new JTextField(5);
        varEntry.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));

        output = new JLabel("");

        calcButton = new JButton("Calculate");
        calcButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));

        calcButton.addActionListener(e ->
        {
           calculate();
        });

        panel.add(new JLabel("   Enter expression here"));
        panel.add(calcEntry);
        panel.add(new JLabel("   Enter variable value here"));
        panel.add(varEntry);
        panel.add(calcButton);
        panel.add(output);
        
        add(panel);
    }

    private void calculate(){
        ArrayList<Token> tList = Lexer.lex(calcEntry.getText());
        if(tList != null){
            Parser p = new Parser(tList);
            Expr e = p.getExpr();

            if(e != null){
                Visitor v = new Visitor();
                Float f;

                if(varEntry.getText().length() == 0){
                    f = v.visit(e);
                }
                else{
                    f = v.visit(e, Float.parseFloat(varEntry.getText()));
                }

                if(f != null){
                    output.setText("   " + f.toString());
                }
                else{
                    output.setText("   Illegal input");
                }

            }
            else{
                output.setText("   Illegal input");
            }

        }
        else{
            output.setText("   Illegal input");
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(450,150);
        m.setVisible(true);
    }
}
