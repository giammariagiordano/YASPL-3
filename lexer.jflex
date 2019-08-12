package cup.example;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;
import java.lang.*;
import java.io.InputStreamReader;

%%

%class Lexer
%implements sym
%public
%unicode
%line
%column
%cup
%char
%{
	StringBuilder lexeme = new StringBuilder();

    public Lexer(ComplexSymbolFactory sf, java.io.InputStream is){
		this(is);
        symbolFactory = sf;
    }
	public Lexer(ComplexSymbolFactory sf, java.io.Reader reader){
		this(reader);
        symbolFactory = sf;
    }
    
    private StringBuffer sb;
    private ComplexSymbolFactory symbolFactory;
    private int csline,cscolumn;

    public Symbol symbol(String name, int code){
		return symbolFactory.newSymbol(name, code,
						new Location(yyline+1,yycolumn+1, yychar), // -yylength()
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength())
				);
    }
    public Symbol symbol(String name, int code, String lexem){
	return symbolFactory.newSymbol(name, code, 
						new Location(yyline+1, yycolumn +1, yychar), 
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), lexem);
    }
    
    protected void emit_warning(String message){
    	System.out.println("scanner warning: " + message + " at : 2 "+ 
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
    
    protected void emit_error(String message){
    	System.out.println("scanner error: " + message + " at : 2" + 
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
%}

Newline    = \r | \n | \r\n
Whitespace = [ \t\f] | {Newline}
/* comments */
Comment = {TraditionalComment} | {EndOfLineComment}
TraditionalComment = "/*" {CommentContent} \*+ "/"
EndOfLineComment = "//" [^\r\n]* {CommentContent}  {Newline}
CommentContent = ( [^*] | \*+[^*/] )*

ID = ([:jletter:] | "_" ) ([:jletterdigit:] | [:jletter:] | "_" )*
INT_CONST = 0 | [1-9][0-9]* /*Decimal number is 0 or start with a number that not is zero followed by 0 or plus digits*/
DOUBLE_CONST = (0 | [1-9][0-9]*)\.[0-9]+
/*String literal*/
STRING_CONST = [^\r\n\"\\]
CHAR_CONST = [:jletterdigit:]


%eofval{
    return symbolFactory.newSymbol("EOF",sym.EOF);
%eofval}

%state STRING
%%

<YYINITIAL> {
							//keywords
    {Whitespace} {                                                 }   
    {Comment}   {                                                   }                                      
	"head"      { return symbolFactory.newSymbol("HEAD", sym.HEAD);    }
    "start"     { return symbolFactory.newSymbol("START", sym.START);   }
    "int"       { return symbolFactory.newSymbol("INT", sym.INT);     }
    "double"    { return symbolFactory.newSymbol("DOUBLE", sym.DOUBLE);}
    "bool"      { return symbolFactory.newSymbol("BOOL", sym.BOOL);    }
    "def"       { return symbolFactory.newSymbol("DEF", sym.DEF);     }
    "true"      { return symbolFactory.newSymbol("TRUE", sym.TRUE);    }
    "false"     { return symbolFactory.newSymbol("FALSE", sym.FALSE);   }
  	"if"        { return symbolFactory.newSymbol("IF", sym.IF);		}
  	"then"      { return symbolFactory.newSymbol("THEN", sym.THEN);	}
  	"else"      { return symbolFactory.newSymbol("ELSE", sym.ELSE);	}
  	"while"     { return symbolFactory.newSymbol("WHILE", sym.WHILE);	}
  	"do"        { return symbolFactory.newSymbol("DO", sym.DO);      }
  	"in"        { return symbolFactory.newSymbol("IN", sym.IN);      }
  	"out"       { return symbolFactory.newSymbol("OUT", sym.OUT);     }
  	"inout"     { return symbolFactory.newSymbol("INOUT", sym.INOUT);   }  
 						 //separators
	";"         { return symbolFactory.newSymbol("SEMI", sym.SEMI); 	}
  	"("         { return symbolFactory.newSymbol("LPAR", sym.LPAR); 	}
  	")"         { return symbolFactory.newSymbol("RPAR", sym.RPAR); 	}
  	"{"         { return symbolFactory.newSymbol("LGPAR", sym.LGPAR);	}
  	"}"         {  return symbolFactory.newSymbol("RGPAR", sym.RGPAR);	} 
    ","         { return symbolFactory.newSymbol("COMMA", sym.COMMA);}
							  //opts
  	"+"         { return symbolFactory.newSymbol("PLUS", sym.PLUS); 	}
  	"<-"        { return symbolFactory.newSymbol("READ", sym.READ);    }
  	"->"        { return symbolFactory.newSymbol("WRITE", sym.WRITE);   }
  	"-"         { return symbolFactory.newSymbol("MINUS", sym.MINUS); 	}
  	"*"         { return symbolFactory.newSymbol("TIMES", sym.TIMES); 	}
  	"/"		    { return symbolFactory.newSymbol("DIV", sym.DIV);		}
  	">"         { return symbolFactory.newSymbol("GT", sym.GT);		}
  	"<"         { return symbolFactory.newSymbol("LT", sym.LT);		}
  	"="         { return symbolFactory.newSymbol("ASSIGN", sym.ASSIGN);	}
  	"=="        { return symbolFactory.newSymbol("EQ", sym.EQ);		}
  	"<="        {  return symbolFactory.newSymbol("LE", sym.LE);		}
  	">="        { return symbolFactory.newSymbol("GE", sym.GE);		}
  	"not"       { return symbolFactory.newSymbol("NOT", sym.NOT);		}
  	"and"       { return symbolFactory.newSymbol("AND", sym.AND);		}
  	"or"        { return symbolFactory.newSymbol("OR", sym.OR);		}
  //	"-"         { return symbolFactory.newSymbol("UMINUS", sym.UMINUS);  }




  {ID}              { return symbolFactory.newSymbol("ID",sym.ID , yytext()); 								  }
  {INT_CONST}       { return symbolFactory.newSymbol( "INT_CONST",sym.INT_CONST, Integer.parseInt(yytext())); 	  }
  {DOUBLE_CONST}    { return symbolFactory.newSymbol( "DOUBLE_CONST",sym.DOUBLE_CONST,  Double.parseDouble(yytext())); }
  {CHAR_CONST}      { return symbolFactory.newSymbol( "CHAR_CONST",sym.CHAR_CONST,(char)Integer.parseInt(yytext()));   }
  
    /*When found " start state string*/
  \" { yybegin(STRING); lexeme.setLength(0); }
  
}

/*Handle String state*/
<STRING> {
\" { yybegin(YYINITIAL); return symbolFactory.newSymbol("STRING_CONST",sym.STRING_CONST, lexeme.toString()); }
/* escape sequences */
  {STRING_CONST}+ { lexeme.append( yytext()); }
  "\\b" 	{ lexeme.append( '\b' ); }
  "\\t" 	{ lexeme.append( '\t' ); }
  "\\n" 	{ lexeme.append( '\n' ); }
  "\\f" 	{ lexeme.append( '\f' ); }
  "\\r" 	{ lexeme.append( '\r' ); }
  "\\\"" 	{ lexeme.append( '\"' ); }
  "\\'" 	{ lexeme.append( '\'' ); }
  "\\\\" 	{ lexeme.append( '\\' ); }

// error fallback
 \\. { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\"");	}
  {Newline} { throw new RuntimeException("Unterminated string at end of line"); }
  }
  <<EOF>>  { return symbolFactory.newSymbol("EOF",sym.EOF); }
  [^]          { emit_warning("Unrecognized character '" +yytext()+"' -- ignored"); }