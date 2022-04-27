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
        if(tokenList.get(index).getOp() == Ops.ABS){
            index++;
            Expr absExpr = add();
            if(index >= tokenList.size() && tokenList.get(index).getOp() !=  Ops.ABS)
                return null;
            else
                return absExpr;
        }
        else
            return add();
    }

    private Expr add(){
        return null;
    }

    private Expr mult(){
        return null;
    }

    private Expr base(){
        return null;
    }

    private Expr type(){
        return null;
    }
}
