/* Generated By:JavaCC: Do not edit this line. Parser.java */
// IMPORTS
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

import ast.*;
import environment.*;

import java.util.HashMap;

/** ID lister. */
public class Parser implements ParserConstants {

  /** Main entry point. */
  public static void main(String args[]) {
    Parser parser = new Parser(System.in);
    ASTNode exp;
        EnvironmentClass env = new EnvironmentClass();    // empty environment

    while (true) {
                try {
                        System.out.print(">>");
                        exp = parser.Start();
                        System.out.println( exp.eval(env) );
                } catch (Exception e) {
                        System.out.println(e);
                        System.out.println ("Syntax Error!");
                        parser.ReInit(System.in);
                }
    }
  }

  static final public ASTNode Start() throws ParseException {
    ASTNode t;
    t = Formula();
    jj_consume_token(EXP_END);
     {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Formula() throws ParseException {
    ASTNode t1;
    ASTNode t2;
    t1 = Relation();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
      case AND:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        jj_consume_token(AND);
        t2 = Relation();
                  t1 = new ASTAnd(t1,t2);
        break;
      case OR:
        jj_consume_token(OR);
        t2 = Relation();
                  t1 = new ASTOr(t1,t2);
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
     {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Relation() throws ParseException {
    ASTNode t1;
    ASTNode t2;
    t1 = Expression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQUAL:
    case DIFFERENT:
    case GREATER_EQUAL:
    case LESSER_EQUAL:
    case GREATER:
    case LESSER:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQUAL:
        jj_consume_token(EQUAL);
        t2 = Expression();
                  t1 = new ASTEq(t1,t2);
        break;
      case DIFFERENT:
        jj_consume_token(DIFFERENT);
        t2 = Expression();
                  t1 = new ASTDiff(t1,t2);
        break;
      case GREATER:
        jj_consume_token(GREATER);
        t2 = Expression();
                  t1 = new ASTGe(t1,t2);
        break;
      case LESSER:
        jj_consume_token(LESSER);
        t2 = Expression();
                  t1 = new ASTLe(t1,t2);
        break;
      case GREATER_EQUAL:
        jj_consume_token(GREATER_EQUAL);
        t2 = Expression();
                  t1 = new ASTGeEq(t1,t2);
        break;
      case LESSER_EQUAL:
        jj_consume_token(LESSER_EQUAL);
        t2 = Expression();
                  t1 = new ASTLeEq(t1,t2);
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
     {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Expression() throws ParseException {
    ASTNode t1;
    ASTNode t2;
    t1 = Term();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        t2 = Term();
                  t1 = new ASTPlus(t1,t2);
        break;
      case MINUS:
        jj_consume_token(MINUS);
        t2 = Term();
                  t1 = new ASTSub(t1,t2);
        break;
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
      {if (true) return t1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Term() throws ParseException {
    ASTNode f1;
    ASTNode f2;
    List<ASTNode> l;
    f1 = Factor();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TIMES:
    case DIVIDE:
    case OPEN_PAR:
    case ASSIGN:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TIMES:
      case DIVIDE:
        label_3:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case TIMES:
            jj_consume_token(TIMES);
            f2 = Factor();
              f1 = new ASTMul(f1, f2);
            break;
          case DIVIDE:
            jj_consume_token(DIVIDE);
            f2 = Factor();
              f1 = new ASTDiv(f1, f2);
            break;
          default:
            jj_la1[6] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case TIMES:
          case DIVIDE:
            ;
            break;
          default:
            jj_la1[7] = jj_gen;
            break label_3;
          }
        }
        break;
      case OPEN_PAR:
        jj_consume_token(OPEN_PAR);
        l = ArgumentList();
        jj_consume_token(CLOSE_PAR);
              f1 = new ASTApply(f1, l);
        break;
      case ASSIGN:
        jj_consume_token(ASSIGN);
        f2 = Factor();
              f1 = new ASTAssign(f1, f2);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
          {if (true) return f1;}
    throw new Error("Missing return statement in function");
  }

  static final public ASTNode Factor() throws ParseException {
    Token n;
    ASTNode e;
    Map<String, ASTNode> m;
    List<String>  s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPEN_PAR:
      jj_consume_token(OPEN_PAR);
      e = Formula();
      jj_consume_token(CLOSE_PAR);
      break;
    case NUMBER:
      n = jj_consume_token(NUMBER);
                  e = new ASTNum(Integer.parseInt(n.image));
      break;
    case BOOLEAN:
      n = jj_consume_token(BOOLEAN);
              e = new ASTBool(Boolean.parseBoolean(n.image));
      break;
    case ID:
      n = jj_consume_token(ID);
                  e = new ASTId(n.image);
      break;
    case MINUS:
      jj_consume_token(MINUS);
      e = Factor();
              e = new ASTSym(e);
      break;
    case NOT:
      jj_consume_token(NOT);
      e = Factor();
          e = new ASTNot(e);
      break;
    case DEREFERENCE:
      jj_consume_token(DEREFERENCE);
      e = Factor();
          e = new ASTDeRef(e);
      break;
    case REFERENCE:
      jj_consume_token(REFERENCE);
      e = Factor();
          e = new ASTRef(e);
      break;
    case LET:
      jj_consume_token(LET);
      m = DefinitionList();
      jj_consume_token(IN);
      e = Formula();
      jj_consume_token(END);
                  e = new ASTLet(m, e);
      break;
    case FUN:
      jj_consume_token(FUN);
      s = ParameterList();
      jj_consume_token(FUN_DEF);
      e = Formula();
      jj_consume_token(END);
                  e = new ASTFun(s, e);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
     {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public List<ASTNode> ArgumentList() throws ParseException {
    ASTNode e;
    List<ASTNode> l;
      l = new LinkedList<ASTNode>();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
    case NUMBER:
    case BOOLEAN:
    case OPEN_PAR:
    case LET:
    case FUN:
    case REFERENCE:
    case ID:
    case NOT:
    case DEREFERENCE:
      e = Formula();
          l.add(e);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[11] = jj_gen;
          break label_4;
        }
        jj_consume_token(COMMA);
        e = Formula();
              l.add(e);
      }
      break;
    default:
      jj_la1[12] = jj_gen;
      ;
    }
      {if (true) return l;}
    throw new Error("Missing return statement in function");
  }

  static final public Map<String, ASTNode> DefinitionList() throws ParseException {
    Token n;
    ASTNode e;
    Map<String, ASTNode> m;
     m = new HashMap<String, ASTNode>();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      n = jj_consume_token(ID);
      jj_consume_token(IS);
      e = Formula();
                  m.put(n.image, e);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[13] = jj_gen;
          break label_5;
        }
        jj_consume_token(COMMA);
        n = jj_consume_token(ID);
        jj_consume_token(IS);
        e = Formula();
                      m.put(n.image, e);
      }
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
          {if (true) return m;}
    throw new Error("Missing return statement in function");
  }

  static final public List<String> ParameterList() throws ParseException {
    Token n;
    List<String> s;
      s = new LinkedList<String>();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      n = jj_consume_token(ID);
                  s.add(n.image);
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[15] = jj_gen;
          break label_6;
        }
        jj_consume_token(COMMA);
        n = jj_consume_token(ID);
                      s.add(n.image);
      }
      break;
    default:
      jj_la1[16] = jj_gen;
      ;
    }
          {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[17];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0xc0000000,0xc0000000,0xc0,0xc0,0x300,0x300,0x8300,0x8300,0x30129480,0x400000,0x30129480,0x400000,0x20000000,0x400000,0x20000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x30,0x30,0xf,0xf,0x0,0x0,0x0,0x0,0x100,0x100,0x240,0x0,0x240,0x0,0x0,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[43];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 17; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 43; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}