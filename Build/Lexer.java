package Build;
import AST.*;
import java.util.*;

/*
The job of the Lexer class is to take the user's String input and turn it into an array of tokens.
This is acheived through the static method lex(). The decision was to make it static instead of an
instance method is because there is no data that would need to be stored in a class.

The concept for the rules are based off of the regular expressions described in the Read Me.

Invalid String entries lead to a return of null.
*/

public class Lexer {
    public static ArrayList<Token> lex(String input){
        //String made lowercase because of constants, logarithmic, and trig expressions
        input = input.toLowerCase();

        //Depth as to help make sure that parentheses are balanced
        int depth = 0;

        //Returns null id empty String is passed through
        if(input.length() == 0){
            return null;
        }

        //Creates empty array of tokens
        ArrayList<Token> tokenList = new ArrayList<Token>(0);

        /*
        This while loop goes through the String, and the character index is tracked using the variable index.
        The decision to use a while loop instead of a for loop is because indexing issues arrised.
        */
        int index = 0;
        while(index < input.length()){
            //Switch statement that executes based off of the character at the current index
            switch(input.charAt(index)){
                /*
                Cases for arithmetic symbols, x, and e are all handled similarly.
                A token of the appropriate type is added to the array and the loop
                increments.
                */
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
                    depth++;
                }
                case '|' -> {
                    tokenList.add(new Token("|", Ops.ABS));
                    index++;
                }
                case ')' -> {
                    tokenList.add(new Token(")", Ops.RP));
                    index++;
                    depth--;
                }
                case 'x' -> {
                    tokenList.add(new Token("x", Ops.VAR));
                    index++;
                }
                case 'e' -> {
                    tokenList.add(new Token("e", Ops.E));
                    index++;
                }

                /*
                This case covers numbers. Number can not start with a decimal.
                */
                case '0','1','2','3','4','5','6','7','8','9' -> {
                    //Number stored in text variable as a String
                    String text = "";

                    //Do while loop cycles through input while still a valid number.
                    do{
                        //Checks for multiple decimals, which would make number invalid
                        if(input.charAt(index) == '.' && text.indexOf(".") != -1)
                            return null;

                        //Adds char to text and increments
                        text += input.charAt(index);
                        index++;

                        //Condition: index is still valid and current char is either a number or a decimal point
                    }while(index < input.length() && ((input.charAt(index) >= 48 && input.charAt(index) <= 57) ||  input.charAt(index) == 46));

                    tokenList.add(new Token(text, Ops.NUM));
                }

                //l is for log and ln. Both go letter by letter to see if full word is formed. If failure, null is returned
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
                //Same process as l is done for s (sin), c (cos), t(tan), and p (pi)
                case 's' -> {
                    index++;
                    if(index < input.length() && input.charAt(index) == 'i'){
                        index++;
                        if(index < input.length() && input.charAt(index) == 'n'){
                            tokenList.add(new Token("sin",Ops.SIN));
                            index++;
                        }
                        else{
                            return null;
                        }
                    }
                    else{
                        return null;
                    }
                }
                case 'c' -> {
                    index++;
                    if(index < input.length() && input.charAt(index) == 'o'){
                        index++;
                        if(index < input.length() && input.charAt(index) == 's'){
                            tokenList.add(new Token("cos",Ops.COS));
                            index++;
                        }
                        else{
                            return null;
                        }
                    }
                    else{
                        return null;
                    }
                }
                case 't' -> {
                    index++;
                    if(index < input.length() && input.charAt(index) == 'a'){
                        index++;
                        if(index < input.length() && input.charAt(index) == 'n'){
                            tokenList.add(new Token("tan",Ops.TAN));
                            index++;
                        }
                        else{
                            return null;
                        }
                    }
                    else{
                        return null;
                    }
                }
                case 'p' -> {
                    index++;
                    if(index < input.length() && input.charAt(index) == 'i'){
                        tokenList.add(new Token("pi",Ops.PI));
                        index++;
                    }
                    else
                        return null;
                }
                //Skips whitespace
                case ' ' -> {index ++;}
                //Invalid character returns null
                default -> {return null;}
            }
        }

        //String of all spaces or unbalanced parentheses returns null
        if(tokenList.size() == 0 || depth != 0)
            return null;
            
        return tokenList;
    }
}
