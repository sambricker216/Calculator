package Build;
import AST.*;

public class Visitor {
    float var;

    public Float visit(Expr e, float x){
        var = x;
        return (Float) e.visit(this);
    }

    public Float visitAbsExpr(AbsExpr abs){
        Float f = null;

        try{
            f = Math.abs( abs.getExpr().visit(this) );
        }
        catch(Exception e){
            return null;
        }

        return f;
    }

    public Float visitConstExpr(ConstExpr con){
        Float f = null;

        try{
            f = con.getVal();
        }
        catch(Exception e){
            return null;
        }
        
        return f;
    }
}
