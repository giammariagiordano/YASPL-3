package semantic;

import java.util.HashMap;

/*
 * Ogni scope è un hashmap e al suo interno c'è un identificatore ed il simbolo semantico associato
 */
public class Scope extends HashMap<Integer, SemanticSymbol> {
  private final Scope parent;
  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  public Scope(Scope parent) {
    super();
    this.parent = parent;
  }


  public Scope getParent() {
    return parent;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    this.forEach((key, value) -> sb.append(String.format("Adds: %d | %s", key, value)));
    return sb.toString();
  }

}
