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
The Lexer is the first main component of the calculator. It's functionality is to iterate through the expression (which is passed to it as a String) and turn it into tokens. The best way of explaining this concept would be using an analogy to English, where this step is effectively turning letters into words. The rules for what characters construct what tokens are made use regular expression concepts, and the specific rules will be detailed below. For example, let's use the expression 0.23 + sin(pi). The Lexer would break this expression down into six different tokens: a number (0.23), a plus, sin, a left parenthesis, pi, and a right parenthesis.
</p>

<h3> Parser </h3>

<p>
The Parser is the second main component of the calculator.
</p>

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
