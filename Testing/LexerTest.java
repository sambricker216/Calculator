package Testing;
import Build.*;
import org.junit.Test;
import AST.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class LexerTest {

    @Test
    public void NumNoDec(){
        ArrayList<Token> tokens = Lexer.lex("1234");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.NUM);
        assertEquals(tokens.get(0).getText(), "1234");
    }

    @Test
    public void NumWithDec(){
        ArrayList<Token> tokens = Lexer.lex("1234.567");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.NUM);
        assertEquals(tokens.get(0).getText(), "1234.567");
    }

    @Test
    public void NumWithTwoDec(){
        ArrayList<Token> tokens = Lexer.lex("1234.56.7");
        assertEquals(tokens, null);
    }

    @Test
    public void NumStartDec(){
        ArrayList<Token> tokens = Lexer.lex(".1234");
        assertEquals(tokens, null);
    }

    @Test
    public void TestAdd(){
        ArrayList<Token> tokens = Lexer.lex("+");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.ADD);
        assertEquals(tokens.get(0).getText(), "+");
    }

    @Test
    public void TestSub(){
        ArrayList<Token> tokens = Lexer.lex("-");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.SUB);
        assertEquals(tokens.get(0).getText(), "-");
    }

    @Test
    public void TestMult(){
        ArrayList<Token> tokens = Lexer.lex("*");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.MULT);
        assertEquals(tokens.get(0).getText(), "*");
    }

    @Test
    public void TestDiv(){
        ArrayList<Token> tokens = Lexer.lex("/");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.DIV);
        assertEquals(tokens.get(0).getText(), "/");
    }

    @Test
    public void TestMod(){
        ArrayList<Token> tokens = Lexer.lex("%");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.MOD);
        assertEquals(tokens.get(0).getText(), "%");
    }

    @Test
    public void TestE(){
        ArrayList<Token> tokens = Lexer.lex("e");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.E);
        assertEquals(tokens.get(0).getText(), "e");
    }

    @Test
    public void TestVar(){
        ArrayList<Token> tokens = Lexer.lex("x");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.VAR);
        assertEquals(tokens.get(0).getText(), "x");
    }

    @Test
    public void TestPow(){
        ArrayList<Token> tokens = Lexer.lex("^");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.POW);
        assertEquals(tokens.get(0).getText(), "^");
    }

    @Test
    public void TestBase(){
        ArrayList<Token> tokens = Lexer.lex("_");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.BASE);
        assertEquals(tokens.get(0).getText(), "_");
    }

    @Test
    public void TestLP(){
        ArrayList<Token> tokens = Lexer.lex("(");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.LP);
        assertEquals(tokens.get(0).getText(), "(");
    }

    @Test
    public void TestRP(){
        ArrayList<Token> tokens = Lexer.lex(")");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.RP);
        assertEquals(tokens.get(0).getText(), ")");
    }

    @Test
    public void TestPI(){
        ArrayList<Token> tokens = Lexer.lex("pi");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.PI);
        assertEquals(tokens.get(0).getText(), "pi");
    }

    @Test
    public void TestLN(){
        ArrayList<Token> tokens = Lexer.lex("ln");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.LN);
        assertEquals(tokens.get(0).getText(), "ln");
    }

    @Test
    public void TestLOG(){
        ArrayList<Token> tokens = Lexer.lex("log");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.LOG);
        assertEquals(tokens.get(0).getText(), "log");
    }

    @Test
    public void TestTwoOps(){
        ArrayList<Token> tokens = Lexer.lex("+-");
        assertEquals(tokens.size(), 2);
        assertEquals(tokens.get(0).getOp(), Ops.ADD);
        assertEquals(tokens.get(0).getText(), "+");
        assertEquals(tokens.get(1).getOp(), Ops.SUB);
        assertEquals(tokens.get(1).getText(), "-");
    }

    @Test
    public void TestOpSpacing(){
        ArrayList<Token> tokens = Lexer.lex("pi ln e *  log ");
        assertEquals(tokens.size(), 5);
        assertEquals(tokens.get(0).getOp(), Ops.PI);
        assertEquals(tokens.get(0).getText(), "pi");
        assertEquals(tokens.get(1).getOp(), Ops.LN);
        assertEquals(tokens.get(1).getText(), "ln");
        assertEquals(tokens.get(2).getOp(), Ops.E);
        assertEquals(tokens.get(2).getText(), "e");
        assertEquals(tokens.get(3).getOp(), Ops.MULT);
        assertEquals(tokens.get(3).getText(), "*");
        assertEquals(tokens.get(4).getOp(), Ops.LOG);
        assertEquals(tokens.get(4).getText(), "log");
    }

    @Test
    public void TestNumsAndOps(){
        ArrayList<Token> tokens = Lexer.lex("log(1.234 +5.67)%piln7");
        assertEquals(tokens.size(), 10);
        assertEquals(tokens.get(0).getOp(), Ops.LOG);
        assertEquals(tokens.get(0).getText(), "log");
        assertEquals(tokens.get(1).getOp(), Ops.LP);
        assertEquals(tokens.get(1).getText(), "(");
        assertEquals(tokens.get(2).getOp(), Ops.NUM);
        assertEquals(tokens.get(2).getText(), "1.234");
        assertEquals(tokens.get(3).getOp(), Ops.ADD);
        assertEquals(tokens.get(3).getText(), "+");
        assertEquals(tokens.get(4).getOp(), Ops.NUM);
        assertEquals(tokens.get(4).getText(), "5.67");
        assertEquals(tokens.get(5).getOp(), Ops.RP);
        assertEquals(tokens.get(5).getText(), ")");
        assertEquals(tokens.get(6).getOp(), Ops.MOD);
        assertEquals(tokens.get(6).getText(), "%");
        assertEquals(tokens.get(7).getOp(), Ops.PI);
        assertEquals(tokens.get(7).getText(), "pi");
        assertEquals(tokens.get(8).getOp(), Ops.LN);
        assertEquals(tokens.get(8).getText(), "ln");
        assertEquals(tokens.get(9).getOp(), Ops.NUM);
        assertEquals(tokens.get(9).getText(), "7");
    }

    @Test
    public void TestSIN(){
        ArrayList<Token> tokens = Lexer.lex("sin");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.SIN);
        assertEquals(tokens.get(0).getText(), "sin");
    }

    @Test
    public void TestCOS(){
        ArrayList<Token> tokens = Lexer.lex("cos");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.COS);
        assertEquals(tokens.get(0).getText(), "cos");
    }

    @Test
    public void TestTAN(){
        ArrayList<Token> tokens = Lexer.lex("tan");
        assertEquals(tokens.size(), 1);
        assertEquals(tokens.get(0).getOp(), Ops.TAN);
        assertEquals(tokens.get(0).getText(), "tan");
    }

    @Test
    public void ErrorTest1(){
        ArrayList<Token> tokens = Lexer.lex("123n");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest2(){
        ArrayList<Token> tokens = Lexer.lex(" ");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest3(){
        ArrayList<Token> tokens = Lexer.lex("lag");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest4(){
        ArrayList<Token> tokens = Lexer.lex("sun");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest5(){
        ArrayList<Token> tokens = Lexer.lex("pp");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest6(){
        ArrayList<Token> tokens = Lexer.lex("lo");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest7(){
        ArrayList<Token> tokens = Lexer.lex("l");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest8(){
        ArrayList<Token> tokens = Lexer.lex(".1234");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest9(){
        ArrayList<Token> tokens = Lexer.lex("cin");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest10(){
        ArrayList<Token> tokens = Lexer.lex("tal");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest11(){
        ArrayList<Token> tokens = Lexer.lex("t");
        assertEquals(tokens, null);
    }

    @Test
    public void ErrorTest12(){
        ArrayList<Token> tokens = Lexer.lex("ta ");
        assertEquals(tokens, null);
    }
}
