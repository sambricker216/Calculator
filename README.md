<h1> Calculator </h1>
 
<h2> Summary</h2>
 
<p>
This calculator takes written out mathematical expressions and calculates their values. This is different from a standard button based calculator because the user does not have to click on a button everytime to add a number or operation, but can instead just type out the full expressions.
</p>
 
<h2> Inspiration and Credits </h2>
 
<p>
The inspiration for this idea was based off of the content from the Univeristy of Florida course, Programming Language Concepts (PLC). The main project in PLC was based around taking a pseudo programming language and translating it into Java. I took this course in the Spring of 2022. Much of the functionality (explained below) is derived from that course and elements from the main project. 
</p>

<h2> Functionality </h2>

<h3> Lexer </h3>

<p>
The Lexer is the first main component of the calculator. It's functionality is to iterate through the expression (which is passed to it as a String) and turn it into tokens. The best way of explaining this concept would be using an analogy to English, where this step is effectively turning letters into words. The rules for what characters construct what tokens are made use regular expression concepts, and the specific rules will be detailed below. For example, let's use the expression 0.23 + sin(pi). The Lexer would break this expression down into six different tokens: a number (0.23), a plus, sin, a left parenthesis, pi, and a right parenthesis. Illegal lexer operations (illegal characters, incorrect numebr formatting) return null and output an error message to the user.
</p>

<h3> Parser </h3>

<p>
The Parser is the second main component of the calculator. It takes the list of tokens created from the Lexer and turns it in a tree like structure. It is set up in a way so that the higher precedence something is in the order of operations, the deeper it is in the tree. The tree is made up of different expression types depending on what it is. For instance, an addition binary expression would be the lowest depth, and a sin expression would be deeper than it. Illegal parser operations (consecutive binary operands, unbalanced parentheses, etc.) return null and output an error message to the user.
 
Here is a visual example of the tree using a flowchart (base on the expression 4 * 3 + sin(2 + pi):
 ![image](https://user-images.githubusercontent.com/94862877/167680103-76e16aaa-4b10-4a2e-8f25-7c7e588e0d52.png)
As you can see, each inner operation goes one level down in the tree's depth from its parent operation. The higher most operation is a binary expression with the operator +. Its immediate left is another binary expression, but with the * operator instead. Both sides of that one are different constant expressions. On the right side of the + binary expression is a type expression with the operator sin. That conatains a binary expression on operator +. That last binary expression than contains two const expressions (2 and pi).
</p>

<h3> Visitor </h3>

<p>
The Visitor is the third and final main component of the Calculator. The Visitor navigates the tree and performs the operations through each visit step. It knows how to perform eac relevant operation through how it calls the visits (each expression type has its own visit method). The Visitor uses a bottom up approach, performing operations on the deepest parts of the tree first, then using that information to perform higher level operations. If there is something illegal done (negative in a log, 0 in denominator, outside of float range) the calculations return null and an error message is output to the user.
 
Using the tree in the example above, the first operations that would be performed would be getting the values of all the const expressions. Next would be the multiplication on the left and the addition on the right. Then, the sin would be performed. All trig operations are done in degrees instead of radians. After that, the multiplication expression and the sin expression would be added together and that would be the final result.
</p>

<h3> Visuals and GUI </h3>

<p> The GUI is done through the Java API JFrame. </p>

<h3> Testing </h3>
<p> All tests in the Testing folder were done using JUnit. </p>

<h2> Regex and Grammar Rules </h2>

<h3> Regex for Tokens </h3>
<ul>
 <li> digit = 0..9 </li>
 <li> number = (digit)* (. (digit)*)? </li>
 <li> add = + </li>
 <li> sub = - </li>
 <li> mult = * </li>
 <li> div = / </li>
 <li> mod = % </li>
 <li> pow = ^ </li>
 <li> sin = sin </li>
 <li> cos = cos </li>
 <li> tan = tan </li>
 <li> pi = pi </li>
 <li> e = e </li>
 <li> log = log </li>
 <li> ln = ln </li>
 <li> base = _ </li>
 <li> lp = ( </li>
 <li> rp = ) </li>
 <li> abs = | </li>
 <li> var = x </li>
</ul>

<h3> CFG for Parser </h3>
<ul>
 <li> expr = addExpr </li>
 <li> addExpr = multExpr ((add | sub) multExpr)* </li>
 <li> multExpr = powExpr ((mult | div | mod) powExpr)* </li>
 <li> powExpr = baseExpr (pow baseExpr)* </li>
 <li> trigExpr = (sin | cos | tan) lp expr rp </li>
 <li> logExpr = ((log | ln) lp expr rp)) | (log base lp expr rp) </li>
 <li> negExpr = sub baseExpr </li>
 <li> baseExpr =  trigExpr | logExpr | var | pi | e | (lp expr rp) | negExpr | number </li>
</ul>
</ul>
