package AST;
import Build.*;

public class BinExpr extends Expr{
    Expr left;
    Expr right;
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

    public Object visit(Visitor visitor){
        return null;
    }

}
