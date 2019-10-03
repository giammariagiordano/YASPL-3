package semantic;

import lexical.StringTable;

public class TreeSymbolTable implements SymbolTable {
  private Scope currentScope;
  private final StringTable table;
  private final Scope root;

  // this is the global scope
  public TreeSymbolTable(StringTable st) {
    currentScope = new Scope(null);
    root = currentScope;
    this.table = st;
  }

  @Override
  public void enterScope() {
    Scope parent = this.getCurrentScope();
    Scope sc = new Scope(parent);
    this.currentScope = sc;
  }

  @Override
  public void exitScope() {
    this.setCurrentScope(getCurrentScope().getParent());
  }

  @Override
  public boolean prob(int addr) {
    return currentScope.containsKey(addr);
  }

  @Override
  public void add(int addr, SemanticSymbol semanticSymbol) {
    this.getCurrentScope().put(addr, semanticSymbol);
  }

  @Override
  public Scope lookup(int addr) {
    Scope lastScope = this.getCurrentScope();
    while (lastScope != null) {
      if (prob(lastScope, addr)) {
        return lastScope;
      } else {
        lastScope = lastScope.getParent();
      }
    }
    return null;
  }

  @Override
  public int findAddr(String lexema) {
    return this.table.getAddr(lexema);
  }

  @Override
  public Scope getCurrentScope() {
    return currentScope;
  }

  @Override
  public StringTable getStringTable() {
    return this.table;
  }

  @Override
  public boolean equalGlobalScope(Scope scope) {
    return root.equals(scope);
  }

  @Override
  public void setCurrentScope(Scope sc) {
    currentScope = sc;

  }

  private boolean prob(Scope sc, int key) {
    return sc.containsKey(key);
  }

}
