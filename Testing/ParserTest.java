package Testing;
import Build.*;
import org.junit.Test;
import AST.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class ParserTest {

    @Test
    public void TestFloatNum(){
        ArrayList<Token> tokens = Lexer.lex("1234");
        Parser p = new Parser(tokens);
        ConstExpr e = (ConstExpr)(p.getExpr());

        assertEquals(1234, e.getVal());
    }

    @Test
    public void TestFloatPi(){
        ArrayList<Token> tokens = Lexer.lex("pi");
        Parser p = new Parser(tokens);
        ConstExpr e = (ConstExpr)(p.getExpr());

        assertEquals((float)Math.PI, e.getVal());
    }

    @Test
    public void TestFloatE(){
        ArrayList<Token> tokens = Lexer.lex("e");
        Parser p = new Parser(tokens);
        ConstExpr e = (ConstExpr)(p.getExpr());

        assertEquals((float)Math.E, e.getVal());
    }

    @Test
    public void TestSin(){
        ArrayList<Token> tokens = Lexer.lex("sin(pi)");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(Ops.SIN, e.getOp());
        assertEquals(null, e.getBase());
        assertEquals((float)Math.PI, ((ConstExpr)(e.getVal())).getVal() );
    }

    @Test
    public void TestCos(){
        ArrayList<Token> tokens = Lexer.lex("cos(pi)");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(Ops.COS, e.getOp());
        assertEquals(null, e.getBase());
        assertEquals((float)Math.PI, ((ConstExpr)(e.getVal())).getVal() );
    }

    @Test
    public void TestTan(){
        ArrayList<Token> tokens = Lexer.lex("taN(pi)");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(Ops.TAN, e.getOp());
        assertEquals(null, e.getBase());
        assertEquals((float)Math.PI, ((ConstExpr)(e.getVal())).getVal() );
    }

    @Test
    public void TestParen(){
        ArrayList<Token> tokens = Lexer.lex("((1776))");
        Parser p = new Parser(tokens);
        ConstExpr e = (ConstExpr)(p.getExpr());

        assertEquals(1776, e.getVal() );
    }

    @Test
    public void TestParen2(){
        ArrayList<Token> tokens = Lexer.lex("(cos((PI)))");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(Ops.COS, e.getOp());
        assertEquals(null, e.getBase());
        assertEquals((float)Math.PI, ((ConstExpr)(e.getVal())).getVal() );
    }
    

    @Test
    public void TestParen3(){
        ArrayList<Token> tokens = Lexer.lex("cos()");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(null, e);
    }

    @Test
    public void TestParen4(){
        ArrayList<Token> tokens = Lexer.lex("()");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(null, e);
    }

    @Test
    public void TestParen5(){
        ArrayList<Token> tokens = Lexer.lex(")(");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(null, e);
    }

    @Test
    public void TestLog(){
        ArrayList<Token> tokens = Lexer.lex("log()");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(null, e);
    }

    @Test
    public void TestLog1(){
        ArrayList<Token> tokens = Lexer.lex("log(e)");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(Ops.LOG, e.getOp());
        assertEquals((float)(Math.E), ((ConstExpr)(e.getVal())).getVal() );
        assertEquals(e.getBase(), null);
    }

    @Test
    public void TestLog2(){
        ArrayList<Token> tokens = Lexer.lex("log(log(e))");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)p.getExpr();
        TypeExpr e1 = (TypeExpr)e.getVal();

        assertEquals(Ops.LOG, e.getOp());
        assertEquals(Ops.LOG, e1.getOp());
        assertEquals(e.getBase(), null);
        assertEquals(e1.getBase(), null);
        assertEquals((float)(Math.E), ((ConstExpr)(e1.getVal())).getVal() );
    }

    @Test
    public void TestLog4(){
        ArrayList<Token> tokens = Lexer.lex("log_(3)(27)");
        Parser p = new Parser(tokens);
        TypeExpr e = (TypeExpr)(p.getExpr());

        assertEquals(Ops.LOG, e.getOp());

        ConstExpr c1 = (ConstExpr) e.getBase();
        ConstExpr c2 = (ConstExpr) e.getVal();

        assertEquals(3, c1.getVal());
        assertEquals(27, c2.getVal());
    }


    @Test
    public void TestMult1(){
        ArrayList<Token> tokens = Lexer.lex("3 * log(pi)");
        Parser p = new Parser(tokens);
        BinExpr e = (BinExpr) p.getExpr();

        assertEquals(Ops.MULT, e.getOp());

        ConstExpr left = (ConstExpr) e.getLeft();
        TypeExpr right = (TypeExpr) e.getRight();

        assertEquals(3, left.getVal());
        assertEquals(Ops.LOG, right.getOp());
        assertEquals((float) Math.PI, ((ConstExpr)(right.getVal())).getVal() );
    }

    @Test
    public void TestDiv1(){
        ArrayList<Token> tokens = Lexer.lex("ln(e) / 2");
        Parser p = new Parser(tokens);
        BinExpr e = (BinExpr) p.getExpr();

        TypeExpr left = (TypeExpr) e.getLeft();
        ConstExpr right = (ConstExpr) e.getRight();

        assertEquals(Ops.DIV, e.getOp());
        assertEquals(Ops.LN, left.getOp());
        assertEquals((float) Math.E, ((ConstExpr)left.getVal()).getVal() );
        assertEquals(2, right.getVal());
    }

    @Test
    public void TestLoopMult(){
        ArrayList<Token> tokens = Lexer.lex("6 * 5 / 4");
        Parser p = new Parser(tokens);
        BinExpr root = (BinExpr) p.getExpr();

        assertEquals(root.getOp(), Ops.DIV);

        BinExpr left = (BinExpr) root.getLeft();
        ConstExpr right = (ConstExpr) root.getRight();

        assertEquals(Ops.MULT, left.getOp());
        assertEquals(6, ((ConstExpr)(left.getLeft())).getVal());
        assertEquals(5, ((ConstExpr)(left.getRight())).getVal());
        assertEquals(4, right.getVal());
    }

    @Test
    public void TestMultError1(){
        ArrayList<Token> tokens = Lexer.lex("* 3");
        Parser p = new Parser(tokens);
        BinExpr e = (BinExpr)(p.getExpr());

        assertEquals(null, e);
    }

    @Test
    public void TestMultError2(){
        ArrayList<Token> tokens = Lexer.lex("6 %");
        Parser p = new Parser(tokens);
        BinExpr e = (BinExpr)(p.getExpr());

        assertEquals(null, e);
    }

    @Test
    public void TestMultError3(){
        ArrayList<Token> tokens = Lexer.lex("4 * 3 /");
        Parser p = new Parser(tokens);
        BinExpr e = (BinExpr)(p.getExpr());

        assertEquals(null, e);
    }

    @Test
    public void TestMultError4(){
        ArrayList<Token> tokens = Lexer.lex("*");
        Parser p = new Parser(tokens);
        BinExpr e = (BinExpr)(p.getExpr());

        assertEquals(null, e);
    }

    @Test
    public void TestMultError5(){
        ArrayList<Token> tokens = Lexer.lex("log(3 *)");
        Parser p = new Parser(tokens);
        BinExpr e = (BinExpr)(p.getExpr());

        assertEquals(null, e);
    }

    @Test
    public void TestLogMult(){
        ArrayList<Token> tokens = Lexer.lex("log(3 * e)");
        Parser p = new Parser(tokens);
        TypeExpr root = (TypeExpr) p.getExpr();

        assertEquals(Ops.LOG, root.getOp());

        BinExpr b = (BinExpr)  root.getVal();
        assertEquals(3, ((ConstExpr)(b.getLeft())).getVal()  );
        assertEquals((float) Math.E, ((ConstExpr)(b.getRight())).getVal()  );
    }

    @Test
    public void TestNeg(){
        ArrayList<Token> tokens = Lexer.lex("-(4)");
        Parser p = new Parser(tokens);
        TypeExpr root = (TypeExpr) p.getExpr();

        assertEquals(Ops.SUB, root.getOp());
        assertEquals(4, ((ConstExpr)(root.getVal())).getVal()  );
    }

    @Test
    public void TestAdd1(){
        ArrayList<Token> tokens = Lexer.lex("3 + log(pi)");
        Parser p = new Parser(tokens);
        BinExpr e = (BinExpr) p.getExpr();

        assertEquals(Ops.ADD, e.getOp());

        ConstExpr left = (ConstExpr) e.getLeft();
        TypeExpr right = (TypeExpr) e.getRight();

        assertEquals(3, left.getVal());
        assertEquals(Ops.LOG, right.getOp());
        assertEquals((float) Math.PI, ((ConstExpr)(right.getVal())).getVal() );
    }

    @Test
    public void TestSub1(){
        ArrayList<Token> tokens = Lexer.lex("3 - log(pi)");
        Parser p = new Parser(tokens);
        BinExpr e = (BinExpr) p.getExpr();

        assertEquals(Ops.SUB, e.getOp());

        ConstExpr left = (ConstExpr) e.getLeft();
        TypeExpr right = (TypeExpr) e.getRight();

        assertEquals(3, left.getVal());
        assertEquals(Ops.LOG, right.getOp());
        assertEquals((float) Math.PI, ((ConstExpr)(right.getVal())).getVal() );
    }

    @Test
    public void TestAddMult(){
        ArrayList<Token> tokens = Lexer.lex("5 * 4 - 6 % 7");
        Parser p = new Parser(tokens);
        BinExpr root = (BinExpr) p.getExpr();
        assertEquals(Ops.SUB, root.getOp());

        BinExpr left = (BinExpr) root.getLeft();
        BinExpr right = (BinExpr) root.getRight();

        assertEquals(Ops.MULT, left.getOp());
        assertEquals(Ops.MOD, right.getOp());

        assertEquals(5, ((ConstExpr)(left.getLeft())).getVal() );
        assertEquals(4, ((ConstExpr)(left.getRight())).getVal() );
        assertEquals(6, ((ConstExpr)(right.getLeft())).getVal() );
        assertEquals(7, ((ConstExpr)(right.getRight())).getVal() );
    }

    @Test
    public void TestNeg1(){
        ArrayList<Token> tokens = Lexer.lex("5+-()");
        Parser p = new Parser(tokens);
        TypeExpr root = (TypeExpr) p.getExpr();
        assertEquals(null, root);
    }

    @Test
    public void TestNeg2(){
        ArrayList<Token> tokens = Lexer.lex("5+-(4)");
        Parser p = new Parser(tokens);
        BinExpr root = (BinExpr) p.getExpr();
        assertEquals(Ops.ADD, root.getOp());
    }
}