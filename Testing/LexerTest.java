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
}
