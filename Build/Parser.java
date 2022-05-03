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

        e = add();

        if(index < tokenList.size() - 1 || (tokenList.get(tokenList.size() - 1).getOp() == Ops.ABS && !(e instanceof AbsExpr))  || tokenList.get(tokenList.size() - 1).getOp() == Ops.LP)
            e = null;
    }

    private Expr add(){
        Expr left = mult();

        if(left == null){
            return null;
        }

        Expr right;

        while(index < tokenList.size() && (tokenList.get(index).getOp() == Ops.ADD || tokenList.get(index).getOp() == Ops.SUB)){
            Ops op = tokenList.get(index).getOp();
            index++;
            right = mult();
            if(right == null){
                return null;
            }
            left = new BinExpr(left, right, op);
        }

        return left;
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
            case VAR ->{
                index++;
                return new VarExpr();
            }
            case LOG, LN, SIN, COS, TAN, SUB ->{
                return type();
            }
            case LP ->{
                index++;

                Expr e = add();

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                index++;

                return e;

            }
            case ABS ->{
                index++;

                Expr e = add();

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.ABS){
                    return null;
                }

                index++;

                return new AbsExpr(e);
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

                Expr base = add();

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

                Expr val = add();

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

                Expr val = add();

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

        else if(tokenList.get(index).getOp() == Ops.SUB){
            index++;

            if(index >= tokenList.size()){
                return null;
            }
            else{
                TypeExpr t = new TypeExpr(Ops.SUB, base());

                if(t.getVal() == null){
                    return null;
                }
                else{
                    return t;
                }
            }
        }
    
        else{
            Ops op = tokenList.get(index).getOp();

            index++;

            if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.LP){
                return null;
            }

            index++;

            Expr val = add();

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
