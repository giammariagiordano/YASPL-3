package cup.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.logging.Logger;
import java_cup.runtime.*;
import lexical.ArrayStringTable;
import lexical.StringTable;
import semantic.StackSymbolTable;
import semantic.SymbolTable;
import syntax.Program;
import visitor.CodeVisitor;
import visitor.SemanticVisitor;
import visitor.SyntaxVisitor;

class Driver {
  private static StringTable stringTable = new ArrayStringTable();
  private static ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();
  private static final String OUTPUT_PATH = System.getProperty("user.home");
  private static StringBuilder pathBuilder = new StringBuilder();
	public static void main(String[] args) throws Exception {
	  InputStream   is = new FileInputStream(new File("input.txt"));
	  Lexer lexer = new Lexer(complexSymbolFactory, is, stringTable);
      Parser parser = new Parser(lexer, complexSymbolFactory);
      Program program = (Program) parser.parse().value;
      SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
      syntaxVisitor.appendRoot(syntaxVisitor.visit(program, null));
      syntaxVisitor.toXml("SyntaxVisitor.xml");
      SymbolTable symbolTable = new StackSymbolTable(stringTable);
      SemanticVisitor semanticVisitor = new SemanticVisitor(symbolTable);
      semanticVisitor.visit(program, Logger.getLogger(Driver.class.getSimpleName()));
     CodeVisitor codeVisitor = new CodeVisitor(symbolTable);
      String testFile = "input.txt";
      String generatedOutput = testFile.replace("txt", "c");
      pathBuilder.append(OUTPUT_PATH).append('/').append(generatedOutput);
      File generatedFile = new File(pathBuilder.toString());
      FileWriter pw = new FileWriter(generatedFile);
      pw.write(codeVisitor.visit(program, symbolTable.getCurrentScope()));
      pw.close();
      /*if(program.getNodeType() == ReturnType.UNDEFINED) {
          throw new Exception("Semantic Error");
      }*/
	}
}