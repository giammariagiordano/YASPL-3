package lexical;

import java.util.Objects;

/* Simbolo lessicale usato per inserire i dati all'interno della stringTable */
public class LexicalSymbol {

  private final String lexema;
  private final int code;

  public LexicalSymbol(String lexema, int code) {
    this.lexema = lexema;
    this.code = code;
  }

  public String getLexema() {
    return lexema;
  }

  public int getCode() {
    return code;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.lexema, this.code);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    LexicalSymbol other = (LexicalSymbol) obj;
    if (code != other.code)
      return false;
    if (lexema == null) {
      if (other.lexema != null)
        return false;
    } else if (!lexema.equals(other.lexema))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "LexicalSymbol [lexema=" + lexema + ", code=" + code + "]";
  }

}
