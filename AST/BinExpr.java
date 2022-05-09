package AST;
import Build.*;

//Container for binary expressions
public class BinExpr extends Expr{
    //holds the left side
    Expr left;

    //holds the right side
    Expr right;

    //Holds the op
    Ops op;

    public BinExpr(Expr left, Expr right, Ops op){
        this.left = left;
        this.right = right;
        this.op = op;
    }

    public Expr getLeft() {
        return left;
    }

    public Ops getOp() {
        return op;
    }

    public Expr getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Left: " + left.toString() + ", Op: " + op + ", Right: " + right.toString();
    }

    //Calls the binExpr visit in the Visitor
    public Float visit(Visitor visitor){
        return visitor.visitBinExpr(this);
    }

}
