package semantic;

import java.util.Objects;
/*generico simbolo aggiunto alla tabella dei simboli*/
public abstract class SemanticSymbol {
  private final ReturnType returnType;
  public SemanticSymbol(ReturnType returnType) {
    this.returnType = returnType;
  }
  public ReturnType getReturnType() {
    return returnType;
  }
  @Override
  public int hashCode() {
    return Objects.hash(this.returnType);
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SemanticSymbol other = (SemanticSymbol) obj;
    if (returnType != other.returnType)
      return false;
    return true;
  }
  @Override
  public String toString() {
    return "SemanticSymbol [returnType=" + returnType + "]";
  }
  
  

}
