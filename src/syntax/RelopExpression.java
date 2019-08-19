package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class RelopExpression extends Expression {
  final Expression leftOperation,rightOperation;
  final String  operation;


  public RelopExpression(Location left, Location right,Expression leftOperation, Expression rightOperation, String op) {
    super(left, right);
    this.leftOperation = leftOperation;
    this.rightOperation = rightOperation;
    this.operation = op;
  }


  public Expression getLeftOperation() {
    return leftOperation;
  }


  public Expression getRightOperation() {
    return rightOperation;
  }


  public String getOperation() {
    return operation;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
   return visitor.visit(this, param);
  }

}
