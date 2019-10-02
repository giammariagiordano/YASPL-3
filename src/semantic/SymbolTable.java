package semantic;

import lexical.StringTable;
/*Usata durante l'analisi semantica e la generazione del codice*/
public interface SymbolTable {
  void enterScope();
  void exitScope();
  boolean prob(int addr);
  void add(int addr, SemanticSymbol semanticSymbol);
  Scope lookup(int addr);
  int findAddr(String lexema);
  Scope getCurrentScope();
  StringTable getStringTable();
  boolean equalGlobalScope (Scope scope);

}
