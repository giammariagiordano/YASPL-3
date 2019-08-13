package syntax;

import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class Program extends YasplNode {
  private final List<Declarations> decls;
  private final List<StatementsNode> statements;
  public Program(Location left, Location right,List<Declarations> decls,List<StatementsNode> statementsNodes) {
    super(left, right);
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

}
