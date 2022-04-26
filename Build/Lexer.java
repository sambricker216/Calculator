package Build;
import AST.*;
import java.util.*;

public class Lexer {
    public static ArrayList<Token> lex(String input){
        if(input.length() == 0){
            return null;
        }

        ArrayList<Token> tokenList = new ArrayList<Token>(0);

        int index = 0;
        while(index < input.length()){
            switch(input.charAt(index)){
                case '+' -> {
                    tokenList.add(new Token("+", Ops.ADD));
                    index++;
                }
                case '-' -> {
                    tokenList.add(new Token("-", Ops.SUB));
                    index++;
                }
                case '*' -> {
                    tokenList.add(new Token("*", Ops.MULT));
                    index++;
                }
                case '/' -> {
                    tokenList.add(new Token("/", Ops.DIV));
                    index++;
                }
                case '%' -> {
                    tokenList.add(new Token("%", Ops.MOD));
                    index++;
                }
                case '^' -> {
                    tokenList.add(new Token("^", Ops.POW));
                    index++;
                }
                case '_' -> {
                    tokenList.add(new Token("_", Ops.BASE));
                    index++;
                }
                case '(' -> {
                    tokenList.add(new Token("(", Ops.LP));
                    index++;
                }
                case ')' -> {
                    tokenList.add(new Token(")", Ops.RP));
                    index++;
                }
                case 'x' -> {
                    tokenList.add(new Token("x", Ops.VAR));
                    index++;
                }
                case 'e' -> {
                    tokenList.add(new Token("e", Ops.E));
                    index++;
                }
                case '0','1','2','3','4','5','6','7','8','9' -> {
                    String text = "";

                    do{
                        if(input.charAt(index) == '.' && text.indexOf(".") != -1)
                            return null;

                        text += input.charAt(index);
                        index++;
                    }while(index < input.length() && input.charAt(index) >= 48 && input.charAt(index) <= 57);

                    tokenList.add(new Token(text, Ops.NUM));
                }
                case 'l' -> {
                    index++;
                    if(index < input.length() && input.charAt(index) == 'o'){
                        index++;
                        if(index < input.length() && input.charAt(index) == 'g'){
                            tokenList.add(new Token("log",Ops.LOG));
                        }
                        else{
                            return null;
                        }
                    }
                    else if(index < input.length() && input.charAt(index) == 'n'){
                        tokenList.add(new Token("ln",Ops.LN));
                    }
                    else{
                        return null;
                    }
                    index++;
                }
                case 's' -> {
                    index++;
                    if(index < input.length() && input.charAt(index) == 'i'){
                        index++;
                        if(index < input.length() && input.charAt(index) == 'n'){
                            tokenList.add(new Token("sin",Ops.SIN));
                        }
                        else{
                            return null;
                        }
                    }
                }
                case 'c' -> {
                    index++;
                    if(index < input.length() && input.charAt(index) == 'o'){
                        index++;
                        if(index < input.length() && input.charAt(index) == 's'){
                            tokenList.add(new Token("cos",Ops.COS));
                        }
                        else{
                            return null;
                        }
                    }
                }
                case 't' -> {
                    index++;
                    if(index < input.length() && input.charAt(index) == 'a'){
                        index++;
                        if(index < input.length() && input.charAt(index) == 'n'){
                            tokenList.add(new Token("tan",Ops.TAN));
                        }
                        else{
                            return null;
                        }
                    }
                }
            }
        }

        return tokenList;
    }
}
