package types.errors;

public class IdUndefinedError extends LanguageError {

    public IdUndefinedError(String id) {
        super(id + " is not defined");
    }

}
