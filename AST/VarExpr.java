package AST;
import Build.*;

//The var expression doesn't hold any data, just the visitor call
//It is effectively a placeholder in the expression tree
public class VarExpr extends Expr{
    public Float visit(Visitor visitor){
        return visitor.visitVarExpr();
    }
}