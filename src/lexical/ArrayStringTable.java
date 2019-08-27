package lexical;

import java.util.ArrayList;

public class ArrayStringTable implements StringTable {
  ArrayList<LexicalSymbol> lexicalSymbols;
  public ArrayStringTable() {
    this.lexicalSymbols = new ArrayList<LexicalSymbol>();
  }

  @Override
  public boolean addLexicalSymbol(String symbol, int code) {
    if(this.lexicalSymbols.stream().anyMatch(l -> l.getLexema().equals(symbol)))
      return false;
    return this.lexicalSymbols.add(new LexicalSymbol(symbol, code));
  }

  @Override
  public int getAddr(String lexema) {
    return this.lexicalSymbols.indexOf(
        this.lexicalSymbols.stream().filter(
            l -> l.getLexema().equals(lexema)).findFirst().get());
  }

  @Override
  public LexicalSymbol getSymbol(int addr) {
    return this.lexicalSymbols.get(addr);
  }

  @Override
  public String getLexema(int add) {
    return this.lexicalSymbols.get(add).getLexema();
  }
  
  @Override
  public String toString() {
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < lexicalSymbols.size(); i++){
          sb.append(String.format("Address:%d | Value:%s\n", i, lexicalSymbols.get(i).getLexema()));
      }
      return sb.toString();
  }
}
