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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Driver {
  private static StringTable stringTable = new ArrayStringTable();
  private static ComplexSymbolFactory complexSymbolFactory = new ComplexSymbolFactory();
  private static final String OUTPUT_PATH = System.getProperty("user.home");
  private static StringBuilder pathBuilder = new StringBuilder();
	public static void main(String[] args) throws Exception {
	  String fileName;
	  Scanner sc = new Scanner(System.in);
	  System.out.println("Insert file name");
	  fileName = sc.nextLine();
	  InputStream   is = new FileInputStream(new File("script/"+fileName+".yaspl"));
	  Lexer lexer = new Lexer(complexSymbolFactory, is, stringTable);
      Parser parser = new Parser(lexer, complexSymbolFactory);
      Program program = (Program) parser.parse().value;
      SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
      syntaxVisitor.appendRoot(syntaxVisitor.visit(program, null));
      syntaxVisitor.toXml(fileName+".xml");
      SymbolTable symbolTable = new StackSymbolTable(stringTable);
      SemanticVisitor semanticVisitor = new SemanticVisitor(symbolTable);
      semanticVisitor.visit(program, Logger.getLogger(Driver.class.getSimpleName()));
     CodeVisitor codeVisitor = new CodeVisitor(symbolTable);
      String generatedOutput = fileName+".c";
      pathBuilder.append(OUTPUT_PATH).append("/ScriptCGenerati/").append(generatedOutput);
      File generatedFile = new File(pathBuilder.toString());
      FileWriter pw = new FileWriter(generatedFile);
      System.out.println(generatedOutput+" saved in: "+pathBuilder.toString());
      pw.write(codeVisitor.visit(program, symbolTable.getCurrentScope()));
      pw.close();
      sc.close();
   //   ProcessBuilder processBuilder = new ProcessBuilder();
      /*now run the program*/
    //processBuilder.command("bash", "-c", "cd "+OUTPUT_PATH+"/ScriptCGenerati/"
       //   + " && clang "+fileName+".c");
      /*List<String> commands = new ArrayList<String>();
      commands.add("cd "+OUTPUT_PATH+"/ScriptCGenerati/");
      commands.add("clang "+fileName+".c");
      commands.add("./a.out");
      processBuilder.command(commands);
      processBuilder.start();*/
      //Process compile = new ProcessBuilder("bash", OUTPUT_PATH+"/ScriptCGenerati/").start();
     /*  ProcessBuilder processBuilder = new ProcessBuilder();
       processBuilder.command("bash", "-c", "cd "+OUTPUT_PATH+"/ScriptCGenerati/"
            + " && clang "+fileName+".c");
      Process execute = processBuilder.command("./a.out").start();*/
      
	}
}