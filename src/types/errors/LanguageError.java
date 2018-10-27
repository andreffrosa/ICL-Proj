package types.errors;

import types.IValue;

public class LanguageError implements IValue {

    private String msg;

    public LanguageError(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
