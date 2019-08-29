package cup.example;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;
import java.lang.*;
import java.io.InputStreamReader;
import lexical.*;
import semantic.*;
import syntax.*;
import visitor.*;
import java.util.HashMap;

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
   private StringTable table;


    public Lexer(ComplexSymbolFactory sf, java.io.InputStream is, StringTable table){
		this(new InputStreamReader(is));
        symbolFactory = sf;
        this.table = table;
    }

    private StringBuffer sb = new StringBuffer();
    private ComplexSymbolFactory symbolFactory;

    public Symbol symbol(String name, int code){
		return symbolFactory.newSymbol(name, code,
						new Location(yyline+1,yycolumn+1 - yylength()),
						new Location(yyline+1,yycolumn+1));
    }

    public Symbol symbol(String name, int code, Object value){
        this.table.addLexicalSymbol(value.toString(), code);
        return symbolFactory.newSymbol(name, code,
    					new Location(yyline+1, yycolumn+1),
    					new Location(yyline+1, yycolumn+yylength()), value);
    }

    protected void emit_warning(String message){
    	System.out.println("scanner warning: " + message + " at : 2 "+
    			(yyline+1) + " " + (yycolumn+1));
    }

    protected void emit_error(String message){
    	System.out.println("scanner error: " + message + " at : 2" +
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
    
    
%}

Newline = \r|\n|\r\n
InputCharacter = [^\r\n]
Whitespace     = {Newline} | [ \t\f]

/* COMMENTS */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {Newline}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

any = .
ID = ([:jletter:] | "_" ) ([:jletterdigit:] | [:jletter:] | "_" )*
INT_CONST = 0 | [1-9][0-9]* /*Decimal number is 0 or start with a number that not is zero followed by 0 or plus digits*/
DOUBLE_CONST = (0 | [1-9][0-9]*)\.[0-9]+
/*String literal*/
STRING_CONST = [^\r\n\"\\]
CHAR_CONST = '({any})?'


%eofval{
    return symbol("EOF",sym.EOF);
%eofval}

%state STRING
%%

<YYINITIAL> {
							//keywords
    {Whitespace} {/*ignore*/}   
    {Comment}   {                                                   }                                      
	"head"      { return symbol("HEAD", sym.HEAD);    }
    "start"     { return symbol("START", sym.START);   }
    "int"       { return symbol("INT", sym.INT);     }
    "double"    { return symbol("DOUBLE", sym.DOUBLE);}
    "bool"      { return symbol("BOOL", sym.BOOL);    }
    "string"	{ return symbol("STRING", sym.STRING);}
    "char"		{ return symbol("CHAR",sym.CHAR);}
    "def"       { return symbol("DEF", sym.DEF);     }
    "true"      { return symbol("TRUE", sym.TRUE);    }
    "false"     { return symbol("FALSE", sym.FALSE);   }
  	"if"        { return symbol("IF", sym.IF);		}
  	"then"      { return symbol("THEN", sym.THEN);	}
  	"else"      { return symbol("ELSE", sym.ELSE);	}
  	"while"     { return symbol("WHILE", sym.WHILE);	}
  	"do"        { return symbol("DO", sym.DO);      }
  	"in"        { return symbol("IN", sym.IN);      }
  	"out"       { return symbol("OUT", sym.OUT);     }
  	"inout"     { return symbol("INOUT", sym.INOUT);   }  
 						 //separators
	";"         { return symbol("SEMI", sym.SEMI); 	}
  	"("         { return symbol("LPAR", sym.LPAR); 	}
  	")"         { return symbol("RPAR", sym.RPAR); 	}
  	"{"         { return symbol("LGPAR", sym.LGPAR);	}
  	"}"         {  return symbol("RGPAR", sym.RGPAR);	} 
    ","         { return symbol("COMMA", sym.COMMA);}
							  //opts
  	"+"         { return symbol("PLUS", sym.PLUS); 	}
  	"<-"        { return symbol("READ", sym.READ);    }
  	"->"        { return symbol("WRITE", sym.WRITE);   }
  	"-"         { return symbol("MINUS", sym.MINUS); 	}
  	"*"         { return symbol("TIMES", sym.TIMES); 	}
  	"/"		    { return symbol("DIV", sym.DIV);		}
  	">"         { return symbol("GT", sym.GT);		}
  	"<"         { return symbol("LT", sym.LT);		}
  	"="         { return symbol("ASSIGN", sym.ASSIGN);	}
  	"=="        { return symbol("EQ", sym.EQ);		}
  	"<="        {  return symbol("LE", sym.LE);		}
  	">="        { return symbol("GE", sym.GE);		}
  	"not"       { return symbol("NOT", sym.NOT);		}
  	"and"       { return symbol("AND", sym.AND);		}
  	"or"        { return symbol("OR", sym.OR);		}
  //	"-"         { return symbol("UMINUS", sym.UMINUS);  }




  {ID}              { return symbol("ID",sym.ID , yytext()); 								  }
  {INT_CONST}       { return symbol( "INT_CONST",sym.INT_CONST, Integer.parseInt(yytext())); 	  }
  {DOUBLE_CONST}    { return symbol( "DOUBLE_CONST",sym.DOUBLE_CONST,  Double.parseDouble(yytext())); }
  {CHAR_CONST}      { return symbol( "CHAR_CONST", sym.CHAR_CONST, yytext().charAt(1));  }
  
    /*When found " start state string*/
  \" { yybegin(STRING); sb.setLength(0); }
  
}

/*Handle String state*/
<STRING> {
\" { yybegin(YYINITIAL); return symbol("STRING_CONST",sym.STRING_CONST, sb.toString()); }
/* escape sequences */
  {STRING_CONST}+ { sb.append( yytext()); }
  "\\b" 	{ sb.append( '\b' ); }
  "\\t" 	{ sb.append( '\t' ); }
  "\\n" 	{ sb.append( '\n' ); }
  "\\f" 	{ sb.append( '\f' ); }
  "\\r" 	{ sb.append( '\r' ); }
  "\\\"" 	{ sb.append( '\"' ); }
  "\\'" 	{ sb.append( '\'' ); }
  "\\\\" 	{ sb.append( '\\' ); }

// error fallback
 \\. { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\"");	}
  {Newline} { throw new RuntimeException("Unterminated string at end of line"); }
  }
  <<EOF>>  { return symbol("EOF",sym.EOF); }
  [^]          { emit_warning("Unrecognized character '" +yytext()+"' -- ignored"); }
