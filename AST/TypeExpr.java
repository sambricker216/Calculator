package AST;
import Build.*;

public class TypeExpr extends Expr{
    Ops op;
    Expr val;
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

    public Float visit(Visitor visitor){
        return visitor.visitTypeExpr(this);
    }
}
