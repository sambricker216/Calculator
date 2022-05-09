package Build;
import AST.*;
import java.util.*;

/* 
The Parser takes the list of tokens and turns them into a tree
of executable expressions. This is done through a recursive
loop.
*/

/*
Order of operations:
Type exressions: LOG, LN, SIN, COS, TAN, SUB / Parentheses
Exponent
Multiplication
Addition
*/

public class Parser {
    //E is the outward most expression.
    Expr e;

    //TokenList is the list of tokens created in the Parser.
    ArrayList<Token> tokenList;

    //index is the current index in the iteration.
    int index;

    //This method returns the main expression
    public Expr getExpr() {
        return e;
    }

    //Returns the main expression as a String
    @Override
    public String toString() {
        return e.toString();
    }

    /*
    Constructor
    Initializes token list and index
    The add being assigned to e is the add() method, 
    add is the lowest in order of operations
    */
    public Parser(ArrayList<Token> t){
        tokenList = t;
        index = 0;

        e = add();

        //Makes sure entire expression is iterated
        if(index < tokenList.size() - 1 || (tokenList.get(tokenList.size() - 1).getOp() == Ops.ABS && !(e instanceof AbsExpr))  || tokenList.get(tokenList.size() - 1).getOp() == Ops.LP)
            e = null;
    }

    /* 
    Add is the lowest in order of operations and therefore the first executed
    */
    private Expr add(){
        //Goes to next thing in order of operations
        Expr left = mult();

        //Returns null if mult is invalid
        if(left == null){
            return null;
        }

        Expr right;

        //Loops for all add expression with same level of precedence, organizes tree so arithemtic is executed left to right
        //For example, 1 + 2 + 3 would do 1 + 2 first and then add three to that
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

    //Mult is next thing in tree
    //Execution is similar to add because they are both binary expressions
    private Expr mult(){
        Expr left = pow();

        if(left == null){
            return null;
        }

        Expr right;

        while(index < tokenList.size() && (tokenList.get(index).getOp() == Ops.MULT || tokenList.get(index).getOp() == Ops.DIV || tokenList.get(index).getOp() == Ops.MOD)){
            Ops op = tokenList.get(index).getOp();
            index++;
            right = pow();
            if(right == null){
                return null;
            }
            left = new BinExpr(left, right, op);
        }

        return left;
    }

    //Pow is a binary expression and handles the same as mult and add
    private Expr pow(){
        Expr left = base();

        if(left == null){
            return null;
        }

        Expr right;

        while(index < tokenList.size() && tokenList.get(index).getOp() == Ops.POW){
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
        //Check for still having a valid index
        if(index >= tokenList.size()){
            return null;
        }

        //Checks for what the current op type is
        switch(tokenList.get(index).getOp()){
            //Num, pi, and e all make contant expressions of the appropriate value
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
            //Var makes a var expression, which is a placeholder in the tree for a variable
            case VAR ->{
                index++;
                return new VarExpr();
            }
            //This case runs all of the type expression cases
            case LOG, LN, SIN, COS, TAN, SUB ->{
                return type();
            }
            //LP Checks for LP, creates the inside expression, and checks for an RP
            case LP ->{
                index++;

                Expr e = add();

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                index++;

                return e;

            }
            //Takes the inside expression and puts it in a ABS
            case ABS ->{
                index++;

                Expr e = add();

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.ABS){
                    return null;
                }

                index++;

                return new AbsExpr(e);
            }
            //All others are invalid and return null
            default -> {
                return null;
            }
        }
    }

    //Type expression are those with an operator and something enclosed in parentheses. For example: SIN, COS, LOG
    private Expr type(){
        if(index >= tokenList.size()){
            return null;
        }

        //LOG case is special because it has to check for the base
        if(tokenList.get(index).getOp() == Ops.LOG){
            index++;

            if(index >= tokenList.size()){
                return null;
            }

            //Check for base
            if(tokenList.get(index).getOp() == Ops.BASE){
                index++;

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.LP){
                    return null;
                }

                index++;

                if(index >= tokenList.size()){
                    return null;
                }

                //Gets base
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

                //Gets value in the log
                Expr val = add();

                if(val == null)
                    return null;

                if(index >= tokenList.size() || tokenList.get(index).getOp() != Ops.RP){
                    return null;
                }

                index++;

                return new TypeExpr(Ops.LOG, val, base);
            }

            //Log without a base handles the same way all other type expressions do
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

        //SUB (makes expression negative, is a special case becuase it might not need parens)
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
    
        //General case: get op and the inner expression and make the appropraite type expression
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
