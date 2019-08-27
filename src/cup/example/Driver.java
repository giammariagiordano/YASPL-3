package cup.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Logger;
import java_cup.runtime.*;
import lexical.ArrayStringTable;
import lexical.StringTable;
import semantic.ReturnType;
import semantic.StackSymbolTable;
import semantic.SymbolTable;
import syntax.Program;
import visitor.SemanticVisitor2;
import visitor.SyntaxVisitor;

class Driver {
  private static StringTable stringTable = new ArrayStringTable();
  private static ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();
  
	public static void main(String[] args) throws Exception {
	  InputStream   is = new FileInputStream(new File("input.txt"));
	  Lexer lexer = new Lexer(complexSymbolFactory, is, stringTable);
      Parser parser = new Parser(lexer, complexSymbolFactory);
      Program program = (Program) parser.parse().value;
      SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
      syntaxVisitor.appendRoot(syntaxVisitor.visit(program, null));
      syntaxVisitor.toXml("SyntaxVisitor1.xml");
      SymbolTable symbolTable = new StackSymbolTable(stringTable);
      SemanticVisitor2 semanticVisitor = new SemanticVisitor2(symbolTable);
      semanticVisitor.visit(program, Logger.getLogger(Driver.class.getSimpleName()));
      if(program.getNodeType() == ReturnType.UNDEFINED) {
          throw new Exception("Semantic Error");
      }
	}
}