package cup.example;

import java_cup.runtime.*;
import syntax.Program;
import visitor.SyntaxVisitor;

class Driver {

	public static void main(String[] args) throws Exception {
		Parser parser = new Parser();
		//parser.parse();
        Program program = (Program) parser.parse().value;
		SyntaxVisitor syntaxVisitor = new SyntaxVisitor();
        syntaxVisitor.appendRoot(syntaxVisitor.visit(program, null));
        syntaxVisitor.toXml("SyntaxVisitor.xml");
	}
	
}