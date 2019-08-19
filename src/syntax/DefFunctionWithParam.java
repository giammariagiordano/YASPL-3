package syntax;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class DefFunctionWithParam extends DefDecl {
  private final IdentifierExpression id;
  private final ArrayList<ParDeclsNode> parDecls;
  private final BodyNode body;
  public DefFunctionWithParam(Location left, Location right,IdentifierExpression identiferExpression, ParDeclsNode parDecls,BodyNode body) {
    super(left, right);
    this.id = identiferExpression;
    this.parDecls = new ArrayList<ParDeclsNode>();
    this.parDecls.add(parDecls);
    this.body = body;
  }
  public DefFunctionWithParam(Location left, Location right,String identiferExpression, ParDeclsNode parDecls,BodyNode body) {
    super(left, right);
    this.id = new IdentifierExpression(left, right, identiferExpression);
    this.parDecls = new ArrayList<ParDeclsNode>();
    this.parDecls.add(parDecls);
    this.body = body;
  }


  public DefFunctionWithParam(Location idxleft, Location idxright, String id, ArrayList<ParDeclsNode> pd, BodyNode body) {
    super(idxleft,idxright);
    this.id = new IdentifierExpression(idxleft, idxright, id);
    this.parDecls = pd;
    this.body = body;
  }
  public IdentifierExpression getId() {
    return id;
  }

  public ArrayList<ParDeclsNode> getParDecls() {
    return parDecls;
  }

  public BodyNode getBody() {
    return body;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
