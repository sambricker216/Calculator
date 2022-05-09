package AST;
import Build.*;

//Container for the const
public class ConstExpr extends Expr{
    //Holds the constant value as a float
    float val;

    public float getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "" + val;
    }

    //Calls the Const visit in the visitor
    public Float visit(Visitor visitor){
        return visitor.visitConstExpr(this);
    }

    public ConstExpr(float val){
        this.val = (float)val;
    }
}