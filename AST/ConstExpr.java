package AST;
import Build.*;

public class ConstExpr extends Expr{
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

    public ConstExpr(float val){
        this.val = (float)val;
    }
}