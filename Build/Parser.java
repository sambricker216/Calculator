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

    public Parser(ArrayList<Token> t){
        tokenList = t;
        index = 0;

        e = abs();
    }

    private Expr abs(){
        return add();
    }

    private Expr add(){
       return mult();

    }

    private Expr mult(){
        return base();
    }

    private Expr base(){
        if(index >= tokenList.size()){
            return null;
        }

        switch(tokenList.get(index).getOp()){
            case NUM -> {
                return new ConstExpr(Float.parseFloat(tokenList.get(index).getText()));
            }
            case PI -> {
                return new ConstExpr((float)(Math.PI));
            }
            case E ->{
                return new ConstExpr((float)(Math.E));
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

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                index++;

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.LP){
                    return null;
                }

                index++;

                Expr val = abs();

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                index++;

                if(val == null || base == null){
                    return null;
                }

                return new TypeExpr(Ops.LOG, val, base);
            }
            else{
                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.LP){
                    return null;
                }

                index++;

                Expr val = abs();

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                if(val == null){
                    return null;
                }

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

            if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                return null;
            }

            if(val == null){
                return null;
            }

            index++;

            return new TypeExpr(op, val);
        }
    }
}
