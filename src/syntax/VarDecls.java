package syntax;

import java.util.Collections;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class VarDecls extends YasplNode {
  private final List<VarDeclaration> varsDeclarations;
  public VarDecls(Location left, Location right,List<VarDeclaration> varDeclarations) {
    super(left, right);
    Collections.reverse(varDeclarations);
    this.varsDeclarations = varDeclarations;
  }

  
  public List<VarDeclaration> getVarsDeclarations() {
    return varsDeclarations;
  }


  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
