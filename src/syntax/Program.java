package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import semantic.Scope;
import semantic.Scopeable;
import visitor.Visitor;

public class Program extends YasplNode implements Scopeable{
  private final List<Declarations> decls;
  private final List<StatementsNode> statements;
  private Scope scope;
  public Program(Location left, Location right,List<Declarations> decls,List<StatementsNode> statementsNodes) {
    super(left, right);
    Collections.reverse(decls);
    Collections.reverse(statementsNodes);
    this.decls = decls;
    this.statements = statementsNodes;
  }

  
  public List<Declarations> getDecls() {
    return decls;
  }


  public List<StatementsNode> getStatements() {
    return statements;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }


  @Override
  public void attachScope(Scope sc) {
    this.scope=sc;
  }


  @Override
  public Scope getAttachScope() {
    return this.scope;
  }

}
