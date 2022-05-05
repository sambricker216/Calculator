package Testing;
import Build.*;
import org.junit.Test;
import AST.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class VisitorTest {
    @Test
    public void TestConstVisit(){
        ArrayList<Token> tokens = Lexer.lex("1234");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1234, f);
    }

    @Test
    public void TestConstVisit2(){
        ArrayList<Token> tokens = Lexer.lex("PI");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals((float)(Math.PI), f);
    }

    @Test
    public void TestConstVisit3(){
        ArrayList<Token> tokens = Lexer.lex("e");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals((float)Math.E, f);
    }

    @Test
    public void TestAddVisit(){
        ArrayList<Token> tokens = Lexer.lex("1 + 2");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(3, f);
    }

    @Test
    public void TestAddVisit2(){
        ArrayList<Token> tokens = Lexer.lex("1 - 2");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(-1, f);
    }

    @Test
    public void TestMultVisit(){
        ArrayList<Token> tokens = Lexer.lex("3 * 2");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(6, f);
    }

    @Test
    public void TestMultVisit2(){
        ArrayList<Token> tokens = Lexer.lex("144 / 12");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(12, f);
    }

    @Test
    public void TestMultVisit3(){
        ArrayList<Token> tokens = Lexer.lex("6 % 4");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(2, f);
    }

    @Test
    public void TestMultVisit4(){
        ArrayList<Token> tokens = Lexer.lex("7.5 % 6.3");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1.2f, f);
    }

    @Test
    public void TestAddMult(){
        ArrayList<Token> tokens = Lexer.lex("1.2 + 3 * 0.6 - 1");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(2.0f, f);
    }

    @Test
    public void TestMult5(){
        ArrayList<Token> tokens = Lexer.lex("4 / 2 * 3");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(6.0f, f);
    }

    @Test
    public void TestNeg(){
        ArrayList<Token> tokens = Lexer.lex("-5");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(-5.0f, f);
    }

    @Test
    public void TestNeg2(){
        ArrayList<Token> tokens = Lexer.lex("1+-5");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(-4.0f, f);
    }

    @Test
    public void TestNeg3(){
        ArrayList<Token> tokens = Lexer.lex("-(1-4)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(3.0f, f);
    }

    @Test
    public void TestSin(){
        ArrayList<Token> tokens = Lexer.lex("sin(0)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(0.0f, f);
    }

    @Test
    public void TestSin2(){
        ArrayList<Token> tokens = Lexer.lex("sin(90)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1.0f, f);
    }

    @Test
    public void TestCos(){
        ArrayList<Token> tokens = Lexer.lex("cos(0)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1.0f, f);
    }

    @Test
    public void TestCos2(){
        ArrayList<Token> tokens = Lexer.lex("cos(90)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(0.0f, f);
    }

    @Test
    public void TestTan(){
        ArrayList<Token> tokens = Lexer.lex("tan(90)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(null, f);
    }

    @Test
    public void TestTan1(){
        ArrayList<Token> tokens = Lexer.lex("tan(0)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(0.0f, f);
    }

    @Test
    public void TestTan2(){
        ArrayList<Token> tokens = Lexer.lex("tan(45)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1.0f, f);
    }

    @Test
    public void TestTan3(){
        ArrayList<Token> tokens = Lexer.lex("tan(45 + -45)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(0.0f, f);
    }

    @Test
    public void TestLN(){
        ArrayList<Token> tokens = Lexer.lex("ln(e)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1f, f);
    }

    @Test
    public void TestLog(){
        ArrayList<Token> tokens = Lexer.lex("log(10)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1f, f);
    }

    @Test
    public void TestLog2(){
        ArrayList<Token> tokens = Lexer.lex("log_(2)(4)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(2f, f);
    }

    @Test
    public void TestDiv0(){
        ArrayList<Token> tokens = Lexer.lex("1/0");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(null, f);
    }

    @Test
    public void TestAbs(){
        ArrayList<Token> tokens = Lexer.lex("|1 - 2|");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1f, f);
    }

    @Test
    public void TestAbs2(){
        ArrayList<Token> tokens = Lexer.lex("|-1|");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1f, f);
    }

    @Test
    public void TestAbs3(){
        ArrayList<Token> tokens = Lexer.lex("1 + -(|-2|)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(-1f, f);
    }

    @Test
    public void TestAbs4(){
        ArrayList<Token> tokens = Lexer.lex("|-4 + |3-4||");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(3, f);
    }

    @Test
    public void TestVar(){
        ArrayList<Token> tokens = Lexer.lex("x + 3");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e, 3.1f);

        assertEquals(6.1f, f);
    }

    @Test
    public void TestVar2(){
        ArrayList<Token> tokens = Lexer.lex("3 * sin(x * 90)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e, 0.0f);

        assertEquals(0.0f, f);
    }

    @Test
    public void TestVar3(){
        ArrayList<Token> tokens = Lexer.lex("x^2");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();

        Float f = v.visit(e, 0.0f);
        Float f2 = v.visit(e, 1.0f);
        Float f3= v.visit(e, 2.0f);
        Float f4 = v.visit(e, 3.0f);
        Float f5 = v.visit(e, 4.0f);

        assertEquals(0.0f, f);
        assertEquals(1.0f, f2);
        assertEquals(4.0f, f3);
        assertEquals(9.0f, f4);
        assertEquals(16.0f, f5);
    }

    @Test
    public void TestPow(){
        ArrayList<Token> tokens = Lexer.lex("3 ^ 3");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(27f, f);
    }

    @Test
    public void TestPow2(){
        ArrayList<Token> tokens = Lexer.lex("(3 + 1) ^ 2");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(16f, f);
    }

    @Test
    public void TestPow3(){
        ArrayList<Token> tokens = Lexer.lex("2 ^ 2 ^ 3");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(64f, f);
    }

    @Test
    public void TestPow4(){
        ArrayList<Token> tokens = Lexer.lex("2 ^ 2 + 3");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(7f, f);
    }

    @Test
    public void TestPow5(){
        ArrayList<Token> tokens = Lexer.lex("3 ^ (3-4)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(1f/3f, f);
    }

    @Test
    public void TestPow6(){
        ArrayList<Token> tokens = Lexer.lex("3 ^ (0.5)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals((float) Math.sqrt(3), f);
    }

    @Test
    public void SuperTest(){
        ArrayList<Token> tokens = Lexer.lex("(3 - (3^(1/2)^2)) + 100.2 - 50.1 * 2 + sin(360)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(0.0f, f);
    }

    @Test
    public void SuperTest2(){
        ArrayList<Token> tokens = Lexer.lex("2 * 14 * 1 ^ 10000");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(28f, f);
    }

    @Test
    public void SuperTest3(){
        ArrayList<Token> tokens = Lexer.lex("ln(e) + log(10 * 10) * log_(1 + 1)(8)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(7f, f);
    }

    @Test
    public void ErrorTest(){
        ArrayList<Token> tokens = Lexer.lex("1/(3-3)");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(null, f);
    }

    @Test
    public void ErrorTest2(){
        ArrayList<Token> tokens = Lexer.lex("-10^45");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(null, f);
    }

    @Test
    public void ErrorTest3(){
        ArrayList<Token> tokens = Lexer.lex("2 + 1^949485950584948384948559605984054955060693");
        Parser p = new Parser(tokens);
        Expr e = p.getExpr();
        Visitor v = new Visitor();
        Float f = v.visit(e);

        assertEquals(null, f);
    }
}