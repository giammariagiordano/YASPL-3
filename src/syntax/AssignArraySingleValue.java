package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class AssignArraySingleValue extends StatementNode implements ArrayInterface{
  private final IdentifierExpression id;
  private final Expression index, value;
  public AssignArraySingleValue(Location left, Location right, IdentifierExpression id, Expression index, Expression value) {
    super(left, right);
    this.id = id;
    this.index = index;
    this.value = value;
  }
  
  

  public IdentifierExpression getId() {
    return id;
  }


  @Override
  public Expression getIndex() {
    return index;
  }



  public Expression getValue() {
    return value;
  }



  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
