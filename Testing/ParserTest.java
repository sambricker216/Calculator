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
}
