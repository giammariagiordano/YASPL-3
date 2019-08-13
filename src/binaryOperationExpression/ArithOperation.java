package binaryOperationExpression;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntax.Expression;
  import visitor.Visitor;
  
public class ArithOperation extends Expression {
  final Expression leftOperation,rightOperation;
  final String  operation;
  public ArithOperation(Location left, Location right,Expression es1,Expression es2,String op) {
    super(left, right);
    this.leftOperation = es1;
    this.rightOperation = es2;
    this.operation = op;
  }

  public Expression getLeftOperation() {
    return leftOperation;
  }
  public Expression getRightOperation() {
    return rightOperation;
  }
  public String getSimbolOperation() {
    return operation;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
