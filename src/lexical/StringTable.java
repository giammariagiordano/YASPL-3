package lexical;

public interface StringTable {
  /* Memorizza gli identificatori usati durante l'analisi lessicale */
  boolean addLexicalSymbol(String symbol, int code);

  int getAddr(String lexema);

  LexicalSymbol getSymbol(int addr);

  String getLexema(int add);
}
