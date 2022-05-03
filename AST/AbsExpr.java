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

    public Float visit(Visitor visitor){
        return visitor.visitAbsExpr(this);
    }
}
