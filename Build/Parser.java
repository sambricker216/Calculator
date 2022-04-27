package Build;
import AST.*;
import java.util.*;

public class Parser {
    Expr e;
    ArrayList<Token> tokenList;
    int index;

    public Parser(ArrayList<Token> t){
        tokenList = t;
        index = 0;

        e = abs();
    }

    private Expr abs(){
        if(index >= tokenList.size())
            return null;
        else if(tokenList.get(index).getOp() == Ops.ABS){
            index++;
            Expr absExpr = add();
            if(index >= tokenList.size() || tokenList.get(index).getOp() !=  Ops.ABS)
                return null;
            else{
                index++;
                return absExpr;
            }
        }
        else
            return add();
    }

    private Expr add(){
        if(index >= tokenList.size())
            return null;

        Expr left = mult();
        Expr right = null;

        while(index <= tokenList.size() && (tokenList.get(index).getOp() == Ops.ADD || tokenList.get(index).getOp() == Ops.ADD)){
            index++;
            right = mult();
            left = new BinExpr(left, right, tokenList.get(index).getOp());
        }

        if(right != null){
            return new BinExpr(left, right, tokenList.get(index).getOp());
        }
        else{
            return left;
        }

    }

    private Expr mult(){
        if(index >= tokenList.size())
            return null;

        Expr left = base();
        Expr right = null;

        while(index <= tokenList.size() && (tokenList.get(index).getOp() == Ops.MULT || tokenList.get(index).getOp() == Ops.DIV || tokenList.get(index).getOp() == Ops.MOD)){
            index++;
            right = base();
            left = new BinExpr(left, right, tokenList.get(index).getOp());
        }

        if(right != null){
            return new BinExpr(left, right, tokenList.get(index).getOp());
        }
        else{
            return left;
        }
    }

    private Expr base(){
        return null;
    }

    private Expr type(){
        return null;
    }
}
