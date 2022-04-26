package Build;
import AST.*;

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
