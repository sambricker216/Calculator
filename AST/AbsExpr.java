package AST;
import Build.*;

public class AbsExpr  extends Expr{
    Expr expr;

    public AbsExpr(Expr e){
        expr = e;
    }

    public Expr getExpr() {
        return expr;
    }

    @Override
    public String toString() {
        return "|" + expr.toString() + "|";
    }

    public Object visit(Visitor visitor){
        return null;
    }
}
