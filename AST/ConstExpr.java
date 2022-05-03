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

    public Float visit(Visitor visitor){
        return null;
    }

    public ConstExpr(float val){
        this.val = (float)val;
    }
}