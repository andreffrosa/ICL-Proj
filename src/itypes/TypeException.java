package itypes;

public class TypeException extends RuntimeException {

    private static final String simpleTypeError = "TypeError: ";
    private static final String singleTypeError = simpleTypeError + "operation \"%s\" expects \"%s\" but got \"%s\"";
    private static final String doubleTypeError = simpleTypeError + "operation \"%s\" expects \"%s ; %s\" but got \"%s ; %s\"";


    public TypeException(String operation, IType expected, IType got) {
        super(String.format(singleTypeError, operation, expected, got));
    }

    public TypeException(String operation, IType expected1, IType expected2, IType got1, IType got2) {
        super(String.format(doubleTypeError, operation, expected1, expected2, got1, got2));
    }

    public TypeException(String customMessage) {
        super(simpleTypeError + customMessage);
    }
}
