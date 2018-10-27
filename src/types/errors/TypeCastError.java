package types.errors;

public class TypeCastError extends LanguageError {

    public TypeCastError(String castFrom, String castTo) {
        super("Cannot cast " + castFrom + " to " + castTo);
    }

}
