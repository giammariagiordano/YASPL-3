package syntax;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class BodyNode extends YasplNode {
  private final ArrayList<VarDeclaration> varDecls;
  private final ArrayList<StatementsNode> statementsNode;
  public BodyNode(Location left, Location right, ArrayList<VarDeclaration> vd, ArrayList<StatementsNode> statements) {
    super(left, right);
    this.varDecls = vd;
    this.statementsNode = statements;
  }

  public BodyNode(Location left, Location right, ArrayList<StatementsNode> statementsNode) {
    super(left, right);
    this.statementsNode = statementsNode;
    varDecls = new ArrayList<VarDeclaration>();
    }
  
  public ArrayList<VarDeclaration> getVarDecls() {
    return varDecls;
  }

  public ArrayList<StatementsNode> getStatementsNode() {
    return statementsNode;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
