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
    public void TestParenError1(){
        ArrayList<Token> tokens = Lexer.lex("(PI");
        Parser p = new Parser(tokens);
        ConstExpr e = (ConstExpr)(p.getExpr());

        assertEquals(e, null);
    }
    
}