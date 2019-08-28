package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.Scope;
import semantic.Scopeable;
import visitor.Visitor;

public class Program extends YasplNode implements Scopeable {
  private final List<DeclarationNode> declsNode;
  private final List<StatementNode> statementsNode;
  private Scope scope;

  public Program(Location left, Location right, List<DeclarationNode> declsNode,
      List<StatementNode> statementsNode) {
    super(left, right);
    Collections.reverse(declsNode);
    Collections.reverse(statementsNode);
    this.declsNode = declsNode;
    this.statementsNode = statementsNode;
  }

  public List<DeclarationNode> getDeclsNode() {
    return declsNode;
  }

  public List<StatementNode> getStatementsNode() {
    return statementsNode;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

  @Override
  public void attachScope(Scope sc) {
    this.scope = sc;
  }

  @Override
  public Scope getAttachScope() {
    return this.scope;
  }
}
