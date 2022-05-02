package Build;
import AST.*;
import java.util.*;

public class Parser {
    Expr e;
    ArrayList<Token> tokenList;
    int index;

    public Expr getExpr() {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    public Parser(ArrayList<Token> t){
        tokenList = t;
        index = 0;

        e = abs();

        if(index < tokenList.size() - 1)
            e = null;
    }

    private Expr abs(){
        return add();
    }

    private Expr add(){
       return mult();

    }

    private Expr mult(){
        Expr left = base();

        if(left == null){
            return null;
        }

        Expr right;

        while(index < tokenList.size() && (tokenList.get(index).getOp() == Ops.MULT || tokenList.get(index).getOp() == Ops.DIV || tokenList.get(index).getOp() == Ops.MOD)){
            Ops op = tokenList.get(index).getOp();
            index++;
            right = base();
            if(right == null){
                return null;
            }
            left = new BinExpr(left, right, op);
        }

        return left;
    }

    private Expr base(){
        if(index >= tokenList.size()){
            return null;
        }

        switch(tokenList.get(index).getOp()){
            case NUM -> {
                ConstExpr c = new ConstExpr(Float.parseFloat(tokenList.get(index).getText()));
                index++;
                return c;
            }
            case PI -> {
                ConstExpr c = new ConstExpr((float)(Math.PI));
                index++;
                return c;
            }
            case E ->{
                ConstExpr c = new ConstExpr((float)(Math.E));
                index++;
                return c;
            }
            case LOG, LN, SIN, COS, TAN ->{
                return type();
            }
            case LP ->{
                index++;

                Expr e = abs();

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                index++;

                return e;

            }
            default -> {
                return null;
            }
        }
    }

    private Expr type(){
        if(index >= tokenList.size()){
            return null;
        }

        if(tokenList.get(index).getOp() == Ops.LOG){
            index++;

            if(index >= tokenList.size()){
                return null;
            }

            if(tokenList.get(index).getOp() == Ops.BASE){
                index++;

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.LP){
                    return null;
                }

                index++;

                if(index >= tokenList.size()){
                    return null;
                }

                Expr base = abs();

                if(base == null)
                    return null;

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                index++;

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.LP){
                    return null;
                }

                index++;

                Expr val = abs();

                if(val == null)
                    return null;

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                index++;

                return new TypeExpr(Ops.LOG, val, base);
            }
            else{
                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.LP){
                    return null;
                }

                index++;

                Expr val = abs();

                if(val == null){
                    return null;
                }

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                index++;

                return new TypeExpr(Ops.LOG, val);
            }
        }
    
        else{
            Ops op = tokenList.get(index).getOp();

            index++;

            if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.LP){
                return null;
            }

            index++;

            Expr val = abs();

            if(val == null){
                return null;
            }

            if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                return null;
            }

            index++;

            return new TypeExpr(op, val);
        }
    }
}
