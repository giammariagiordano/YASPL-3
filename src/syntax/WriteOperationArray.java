package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class WriteOperationArray extends StatementNode implements ArrayInterface {

  private final IdentifierExpression id;
  private final Expression index;

  public WriteOperationArray(Location left, Location right, IdentifierExpression id, Expression index) {
    super(left, right);
    this.id=id;
    this.index=index;
  }
  

  public IdentifierExpression getId() {
    return id;
  }

  @Override
  public Expression getIndex() {
    return index;
  }



  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param); 
  }

}
