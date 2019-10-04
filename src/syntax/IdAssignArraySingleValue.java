package syntax;

import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class IdAssignArraySingleValue extends StatementNode implements ArrayInterface {
  
  private final IdentifierExpression idLeft, idRight;
  private final Expression index;

  public IdAssignArraySingleValue(Location left, Location right, IdentifierExpression idLeft, IdentifierExpression idRight,
     Expression index ) {
    super(left, right);
    this.idLeft=idLeft;
    this.idRight=idRight;
    this.index=index;
  }
  
  



  public IdentifierExpression getIdLeft() {
    return idLeft;
  }



  public IdentifierExpression getIdRight() {
    return idRight;
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
