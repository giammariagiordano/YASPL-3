package cup.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    /*  System.out.println(OUTPUT_PATH+"/ScriptCGenerati/"+fileName);
      Runtime.getRuntime().exec("gcc /home/broke/ScriptCGenerati/input.c && ./a.out\n");
      Runtime.getRuntime().exec("gcc "+OUTPUT_PATH+fileName+".c"+ "&& ./a.out\n");
      //Runtime.getRuntime().exec("sh -c cd "+OUTPUT_PATH+"/ScriptCGenerati/"+" && clang "+fileName+".c && ./a.out" );
      */

     /* String command= "/usr/bin/x-terminal-emulator -e /home/broke/ScriptCGenerati/"; 
      Runtime rt = Runtime.getRuntime();  
      Process pr = rt.exec(command);*/
      /*String pathFile = OUTPUT_PATH+"/ScriptCGenerati/"+fileName+".c";
     compileCFile(pathFile);
     //runCFile(pathFile);
      execute("./a.out");*/
	}
	
	/*public static void compileCFile(String fileName)
    {
        String compileFileCommand = "gcc " + fileName;

       String resultString = "";
        try
        {
            System.out.println("Compiling C File");

            Process processCompile = Runtime.getRuntime().exec(compileFileCommand);

            BufferedReader brCompileError = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            String errorCompile = brCompileError.readLine();
            if (errorCompile != null)
                System.out.println("Error Compiler = " + errorCompile);

            resultString += errorCompile +"\n";

            BufferedReader brCompileRun = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            String outputCompile = brCompileRun.readLine();
            if (outputCompile != null)
                System.out.println("Output Compiler = " + outputCompile);

            resultString += outputCompile +"\n";

        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
    }
	

    static void runCFile(String fileName)
    {
      String runFileCommand = "./a.out";
      
        try
        {
            System.out.println("Running C File");

            Process processRun = Runtime.getRuntime().exec(runFileCommand);

            BufferedReader brRun = new BufferedReader(new InputStreamReader(processRun.getErrorStream()));
            String errorRun = brRun.readLine();
            if (errorRun != null)
                System.out.println("Error Run = " + errorRun);

            BufferedReader brResult = new BufferedReader(new InputStreamReader(processRun.getInputStream()));
            String outputRun = brResult.readLine();
            if (outputRun != null)
                System.out.println("Output Run = " + outputRun);

        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
    }
    public static String execute(String command) {
      String[] commands = new String[] { "/bin/sh", "-c", command };

      ExecutorService executor = Executors.newCachedThreadPool();
      try {
          ProcessBuilder builder = new ProcessBuilder(commands);

          Process proc = builder.start();
          CollectOutput collectStdOut = new CollectOutput(
                  proc.getInputStream());
          executor.execute(collectStdOut);

          CollectOutput collectStdErr = new CollectOutput(
                  proc.getErrorStream());
          executor.execute(collectStdErr);
         
          // merges standard error and standard output
          builder.redirectErrorStream();
          Process proc = builder.start();*/
	/*
          CollectOutput out = new CollectOutput(proc.getInputStream());
          executor.execute(out);
          // 
          // child proc exit code
          int waitFor = proc.waitFor();

          return out.get();
      } catch (IOException e) {
          return e.getMessage();
      } catch (InterruptedException e) {
          // proc maybe interrupted
          e.printStackTrace();
      }
      return null;
  }

  public static class CollectOutput implements Runnable {
      private final StringBuffer buffer = new StringBuffer();
      private final InputStream inputStream;

      public CollectOutput(InputStream inputStream) {
          super();
          this.inputStream = inputStream;
      }

     
      @Override
      public void run() {
          BufferedReader reader = null;
          String line;
          try {
              reader = new BufferedReader(new InputStreamReader(inputStream));
              while ((line = reader.readLine()) != null) {
                  buffer.append(line).append('\n');
              }
          } catch (Exception e) {
              System.err.println(e);
          } finally {
              try {
                  reader.close();
              } catch (IOException e) {
              }
          }

      }

      public String get() {
          return buffer.toString();
      }
  }*/
}