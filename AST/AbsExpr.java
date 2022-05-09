package AST;
import Build.*;

//Container for abs expressions
public class AbsExpr  extends Expr{
    //Holds the nested expression
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

    //Calls the visit in visitor
    public Float visit(Visitor visitor){
        return visitor.visitAbsExpr(this);
    }
}
