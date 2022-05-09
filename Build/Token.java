package Build;
import AST.*;

//The token class is the basis for the tokens created in the lexer, I holds the op and a String that contains the text of the content it holds
public class Token {
    String text;
    Ops op;

    public Token(String s, Ops o){
        text = s;
        op = o;
    }

    public Ops getOp() {
        return op;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Text: " + text + ", Kind: " + op;
    }
}
