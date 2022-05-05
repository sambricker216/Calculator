package Build;
import AST.*;

public class Visitor {
    float var;

    public Float visit(Expr e, float x){
        var = x;

        Float f;
        try{
            f =  (Float) e.visit(this);
        }
        catch(Exception ex){
            return null;
        }

        if(f.isNaN() || f.isInfinite()){
            return null;
        }

        return f;
    }

    public Float visit(Expr e){
        var = 0.0f;

        Float f;
        try{
            f =  (Float) e.visit(this);
        }
        catch(Exception ex){
            return null;
        }

        if(f.isNaN() || f.isInfinite()){
            return null;
        }

        return f;
    }

    public Float visitVarExpr(){
        return var;
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

    public Float visitBinExpr(BinExpr bin){
        Float left = bin.getLeft().visit(this);
        Float right = bin.getRight().visit(this);

        if(left == null || right == null){
            return null;
        }

        Float f = null;

        try{
            switch(bin.getOp()){
                case ADD ->{
                    f = left + right;
                }
                case SUB ->{
                    f = left - right;
                }
                case MULT ->{
                    f = left * right;
                }
                case DIV ->{
                    f = left / right;

                    if(right == 0.0f){
                        return null;
                    }
                }
                case MOD ->{
                    f = left % right;
                    f *= 1000;
                    int num = Math.round(f);
                    f = (float) num / 1000f;
                }
                case POW ->{
                    f = (float) Math.pow(left, right);
                }
                default -> {
                    return null;
                }
            }
            
        }
        catch(Exception e){
            return null;
        }

        return f;
    }

    public Float visitTypeExpr(TypeExpr type){
        if(type.getVal() == null){
            return null;
        }

        Float expr = type.getVal().visit(this);

        if(expr == null){
            return null;
        }

        Float f = null;

        try{
            switch(type.getOp()){
                case SUB ->{
                    f = expr * -1.0f;
                }
                case SIN ->{
                    f = (float) Math.sin(Math.toRadians((double) expr));
                    if(expr % 180 == 0){
                        f = 0.0f;
                    }
                }
                case COS ->{
                    f = (float) Math.cos(Math.toRadians((double) expr));
                    if(expr % 180 == 90){
                        f = 0.0f;
                    }
                }
                case TAN ->{
                    f = (float) Math.tan(Math.toRadians((double) expr));
                    if(Math.abs(expr) % 90 == 0 && ((int)(Math.abs(expr)))/90 % 2 == 1 ){
                        return null;
                    }
                }
                case LN ->{
                    if(expr == (float) Math.E){
                        return 1f;
                    }
                    f = (float) (Math.log(expr)/Math.log(Math.E));
                }
                case LOG ->{
                    if(type.getBase() != null){
                        Float base = type.getBase().visit(this);
                        if(base == null){
                            return null;
                        }
                        f = (float) (Math.log(expr)/Math.log(base));
                    }
                    else{
                        f = (float) Math.log10(expr);
                    }
                }
                default -> {
                    return null;
                }
            }
        }
        catch(Exception e){
            return null;
        }

        return f;
    }
}
