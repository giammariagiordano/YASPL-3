package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/*
 * It is used for boolean operation (and, or)
 */
public class BooleanOperation extends Expression {
  final Expression leftOperand, rightOperand;
  final String operation;

  /*
   * Create a new boolean operation
   * 
   * @param left for left location
   * 
   * @param right for right location
   * 
   * @param leftOperand for left operand
   * 
   * @param rightOperand for right operand
   * 
   * @param op for type of operation (and, or)
   */
  public BooleanOperation(Location left, Location right, Expression leftOperand,
      Expression rightOperand, String op) {
    super(left, right);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
    this.operation = op;
  }

  /*
   * Get left operand of expression
   * 
   * @return left operand for booleanOperation
   */

  public Expression getLeftOperand() {
    return leftOperand;
  }

  /*
   * Get right operand of expression
   * 
   * @return right operand for booleanOperation
   */
  public Expression getRightOperand() {
    return rightOperand;
  }

  /*
   * get operation of expression
   * 
   * @return operation for booleanOperation
   */
  public String getOperation() {
    return operation;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
