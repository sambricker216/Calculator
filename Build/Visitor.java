package Build;
import AST.*;

//The visitor class navigates through the tree and performs the calculations using a bottom up approach
//Illegal operations, such as dividing by zero or going outside the range of a float, return null
public class Visitor {
    Float var;

    //Function call with a variable
    public Float visit(Expr e, float x){
        //Variable set to appropriate value
        var = x;

        //Try catch for sake of airthmetic exceptions
        Float f;
        try{
            //Calls visit method from e's class
            f =  (Float) e.visit(this);
        }
        catch(Exception ex){
            return null;
        }

        //Makes sure only valud returns
        if(f == null || f.isNaN() || f.isInfinite()){
            return null;
        }

        return f;
    }

    //Function call no variable
    //Other than var, handles the same as the other overload
    public Float visit(Expr e){
        //Assigns null so expression with var also returns null
        var = null;

        Float f;
        try{
            f =  (Float) e.visit(this);
        }
        catch(Exception ex){
            return null;
        }

        if(f == null || f.isNaN() || f.isInfinite()){
            return null;
        }

        return f;
    }

    //Returns the value of var, null if it's a no var expression
    public Float visitVarExpr(){
        return var;
    }

    //Abs returns the absolute value of the inner expression
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

    //Const returns the value held by the ConstExpr, these are the numerical values
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
        //Binary expressions get right and left hand sides and perform an operation on the two values
        Float left = bin.getLeft().visit(this);
        Float right = bin.getRight().visit(this);

        if(left == null || right == null){
            return null;
        }

        Float f = null;

        //Switches through each value
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

                    //Some cases divising by zero did not become an error so this has to be manually checked for
                    if(right == 0.0f){
                        return null;
                    }
                }
                case MOD ->{
                    f = left % right;
                    //Extra statements to avoid rounding errors
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

        //Gets inside expression
        Float expr = type.getVal().visit(this);

        if(expr == null){
            return null;
        }

        Float f = null;

        try{
            // Switch staements for all the type expressions, trig expression are done in degrees
            //Trig expresions also have special case handling because of rounding issues
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
