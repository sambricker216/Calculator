package AST;
import Build.*;

public class VarExpr extends Expr{
    public Float visit(Visitor visitor){
        return visitor.visitVarExpr();
    }
}