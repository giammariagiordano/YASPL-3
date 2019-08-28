package syntax;

import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class BodyNode extends YasplNode {
  private final List<VarDeclaration> varDecls;
  private final List<StatementNode> statementsNode;

  public BodyNode(Location left, Location right, List<VarDeclaration> vd,
      List<StatementNode> statements) {
    super(left, right);
    this.varDecls = vd;
    this.statementsNode = statements;
  }

  public BodyNode(Location left, Location right, List<StatementNode> statementsNode) {
    super(left, right);
    this.statementsNode = statementsNode;
    varDecls = new ArrayList<VarDeclaration>();
  }

  public List<VarDeclaration> getVarDecls() {
    return varDecls;
  }

  public List<StatementNode> getStatementsNode() {
    return statementsNode;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
}
