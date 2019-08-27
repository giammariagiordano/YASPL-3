package syntax;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.*;

public class ReadOperation extends StatementsNode {
private final Vars vars;
  public ReadOperation(Location left, Location right, Vars vars) {
    super(left, right);
    this.vars = vars;
  }

  public Vars getVars() {
    return vars;
  }

  public ReadOperation(Location varsxleft, Location varsxright,
      ArrayList<IdentifierExpression> vars2) {
    super(varsxleft,varsxright);
    this.vars = new Vars(varsxleft,varsxright,vars2);
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }
  
  
  public String variableDomain(){
   // return this.vars.getIds().stream().map(v -> v.g).collect(Collectors.joining("X"));
    return this.vars.getIds().stream().map(v -> v.getNodeType().getValue()).collect(Collectors.joining("X"));
}

/**
 * check if the input domain and the type domain are compatible
 * @return true if the two domain are equal. False otherwise
 
public boolean checkInputValidity(){
    return this.variableDomain().equals(typeDomain());
}*/
}
