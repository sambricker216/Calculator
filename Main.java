import java.util.*;
import java.awt.*;
import javax.swing.*;
import Build.*;
import AST.*;

//The Main class holds all of the things related to the UI
//The UI is done through JFrame
public class Main extends JFrame{
    //Component of JFrame
    JPanel panel;

    //Text for calculated output
    JLabel output;

    //Text entry field for the primary expression
    JTextField calcEntry;

    //Text entry field for the variabel value
    JTextField varEntry;

    //Button to call the calculation
    JButton calcButton;

    public Main(){
        //Window title
        super("Calculator");

        //Sets window in middle of screen
        setLocationRelativeTo(null);
        
        //makes Jpanel in grid format
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

        //On click calls calculate()
        calcButton.addActionListener(e ->
        {
           calculate();
        });

        //Puts all the components together
        panel.add(new JLabel("   Enter expression here"));
        panel.add(calcEntry);
        panel.add(new JLabel("   Enter variable value here"));
        panel.add(varEntry);
        panel.add(calcButton);
        panel.add(output);
        
        add(panel);
    }

    //Performs the calculation and sets the value to output
    //Any invalid results make the output "Illegal input"
    private void calculate(){
        //Runs lexer
        ArrayList<Token> tList = Lexer.lex(calcEntry.getText());

        //Checks for valid lexer
        if(tList != null){
            //Runs parser and gets main expression
            Parser p = new Parser(tList);
            Expr e = p.getExpr();

            //Checks main expression
            if(e != null){
                Visitor v = new Visitor();
                Float f;

                //if branch runs without variable, else branch runs with
                if(varEntry.getText().length() == 0){
                    f = v.visit(e);
                }
                else{
                    Float var;

                    //Checks if var is a const symbol (pi or e)
                    if(varEntry.getText().toLowerCase().equals("pi")){
                        var = (float) Math.PI;
                    }
                    else if(varEntry.getText().toLowerCase().equals("e")){
                        var = (float) Math.E;
                    }
                    else{
                        try{
                            var = Float.parseFloat(varEntry.getText());
                        }
                        catch(Exception error){
                            var = null;
                        }
                    }

                    //Checks of var is valid
                    if(var == null){
                        f = null;
                    }
                    else{
                        f = v.visit(e, var);
                    }
                }

                //Checks if calculated value is valid
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

    //Main method
    public static void main(String[] args) {
        //Makes new UI
        Main m = new Main();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(450,150);
        m.setVisible(true);
    }
}
