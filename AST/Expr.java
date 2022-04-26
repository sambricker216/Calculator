package AST;
import Build.*; 

public abstract class Expr {
    public abstract Object visit(Visitor visitor);
}
