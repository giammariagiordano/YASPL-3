package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class InitForOperation extends YasplNode {
  final TypeNode type;
  final IdentifierExpression id;
  final Expression expr;

  public InitForOperation(Location left, Location right, TypeNode type, IdentifierExpression id,
      Expression expr) {
    super(left, right);
    this.type = type;
    this.id = id;
    this.expr = expr;
  }

  public InitForOperation(Location left, Location right, IdentifierExpression id, Expression expr) {
    super(left, right);
    this.type = null;
    this.id = id;
    this.expr = expr;
  }

  public InitForOperation(Location left, Location right) {
    super(left, right);
    this.type = null;
    this.id = null;
    this.expr = null;
  }

  public TypeNode getType() {
    return type;
  }

  public IdentifierExpression getId() {
    return id;
  }

  public Expression getExpr() {
    return expr;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
