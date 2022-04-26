package AST;
import Build.*;

public class ConstExpr {
    float val;

    public float getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "" + val;
    }

    public Object visit(Visitor visitor){
        return null;
    }
}
