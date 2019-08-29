package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class ArithOperation extends Expression {
  final Expression leftOperand, rightOperand;
  final String operation;

  public ArithOperation(Location left, Location right, Expression leftOperand,
      Expression rightOperand, String op) {
    super(left, right);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    this.operation = op;
  }

  public Expression getLeftOperand() {
    return leftOperand;
  }

  public Expression getRightOperand() {
    return rightOperand;
  }

  public String getOperation() {
    return operation;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
