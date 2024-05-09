package Core.Parser.AST.Expressions;

public class BinaryExpression implements Expression{

    char operator;
    double num1;
    double num2;
    public BinaryExpression(char operator, double num1, double num2){
        this.operator = operator;
        this.num1 = num1;
        this.num2 = num2;
    }
    @Override
    public double eval() {
        switch (operator){
            case '-':
                return(num1 - num2);
            case '*':
                return(num1 * num2);
            case '/':
                return(num1 / num2);
            case '+':
            default:
                return(num1 + num2);
        }
    }
}
