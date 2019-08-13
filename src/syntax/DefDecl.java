package syntax;

import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public abstract class DefDecl extends Declarations {
 // List<DefFunctionWithParam> defFunctionWithParam;

  public DefDecl(Location left, Location right) {
    super(left, right);
    // TODO Auto-generated constructor stub
  }

  /*public DefDecl(Location pdxleft, Location pdxright, ParDeclsNode pd, BodyNode body) {
    super(pdxleft,pdxright);
    this.pdn = pd;
    this.body = body;
  }
  public DefDecl(Location pdxleft, Location pdxright, BodyNode body) {
    super(pdxleft,pdxright);
    
    this.body = body;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }*/

}
