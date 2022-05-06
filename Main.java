import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame{
    JPanel panel;
    JLabel output;
    JTextField calcEntry;
    JTextField varEntry;

    public Main(){
        super("Calculator");
        setLocationRelativeTo(null);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));

        calcEntry = new JTextField(15);
        varEntry = new JTextField(5);
        output = new JLabel("");

        panel.add(new JLabel("Enter expression here"));
        panel.add(calcEntry);
        panel.add(new JLabel("Enter variable value here"));
        panel.add(varEntry);
        panel.add(new JButton("Calculate"));
        panel.add(output);
        
        add(panel);
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(450,150);
        m.setVisible(true);
    }
}
