package syntax;

import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.ComplexSymbolFactory.Location;
import visitor.Visitor;

public class ParDeclsNode extends YasplNode {
 private final List<ListParams> listParam;
  public ParDeclsNode(Location left, Location right, List<ListParams> listParam) {
    super(left, right);
    this.listParam = listParam;
  }

  public ParDeclsNode(Location left, Location right,ParType pt,TypeNode type, String id) {
    super(left, right);
    this.listParam = new ArrayList<ListParams>();
    listParam.add(new ListParams(left, right, pt, type, new IdentifierExpression(left,right,id)));
  }

  public List<ListParams> getListParam() {
    return listParam;
  }

  @Override
  public <T, P> T accept(Visitor<T, P> visitor, P param) {
    return visitor.visit(this, param);
  }

}
