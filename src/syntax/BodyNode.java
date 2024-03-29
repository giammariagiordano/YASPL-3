package syntax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

/**
 * Create a Body node that contains VarDeclaration and Statement For example: { int a; if(condition)
 * { } }
 */

public class BodyNode extends YasplNode {
  private final List<VarDeclaration> varDecls;
  private final List<StatementNode> statementsNode;

  /**
   * Create a new Body node
   * 
   * @param left for left location
   * @param right for right location
   * @param declsNode for list of declarations
   * @param statementsNode for list of statements
   */

  public BodyNode(Location left, Location right, List<VarDeclaration> vd,
      List<StatementNode> statements) {
    super(left, right);
    Collections.reverse(vd);
    this.varDecls = vd;
    Collections.reverse(statements);
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
