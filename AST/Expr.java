package AST;
import Build.*; 

//Abstract base class for all the expressions
public abstract class Expr {
    public abstract Float visit(Visitor visitor);
}
