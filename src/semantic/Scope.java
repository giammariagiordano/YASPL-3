package semantic;

import java.util.HashMap;
/*Ogni scope è un hashmap e al suo interno c'è un identificatore ed il simbolo semantico associato*/
public class Scope extends HashMap<Integer, SemanticSymbol> {
  
/**
   * 
   */
  private static final long serialVersionUID = 1L;

@Override
public String toString() {
  StringBuilder sb = new StringBuilder();
  this.forEach((key, value) -> sb.append(String.format("Adds: %d | %s",key,value)));
  return sb.toString();
}
  
}