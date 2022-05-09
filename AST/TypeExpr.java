package AST;
import Build.*;

//Container for the type expression
public class TypeExpr extends Expr{
    //Holds which op is being used
    Ops op;

    //Holds the value the op is being applied to
    Expr val;

    //Holds the base, this is only relevant to log
    Expr base;

    public TypeExpr(Ops op, Expr val){
        this.op = op;
        this.val = val;
    }

    public TypeExpr(Ops op, Expr val, Expr base){
        this.op = op;
        this.val = val;
        this.base = base;
    }

    public Expr getBase() {
        return base;
    }

    public Ops getOp() {
        return op;
    }

    public Expr getVal() {
        return val;
    }

    @Override
    public String toString() {
        if(base == null){
            return "Op: " + op + ", Val" + val.toString();
        }
        else{
            return "Op: " + op + ", Base: " + base.toString() + ", Val" + val.toString();
        }
    }

    //Calls the type expression visit in the visitor
    public Float visit(Visitor visitor){
        return visitor.visitTypeExpr(this);
    }
}
