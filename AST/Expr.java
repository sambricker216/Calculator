package AST;
import Build.*; 

public abstract class Expr {
    public abstract Float visit(Visitor visitor);
}
