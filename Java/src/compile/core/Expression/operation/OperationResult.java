package compile.core.Expression.operation;

import compile.core.Lexer.RegExp.LexemType;

public class OperationResult {
    private final LexemType resultType;
    private int intResult;
    private String stringResult;

    public OperationResult(LexemType resultType) {
        this.resultType = resultType;
    }

    public OperationResult(OperationResult res){
        this.resultType = res.getResultType();
        this.intResult = res.getIntResult();
        this.stringResult = res.getStringResult();
    }

    public OperationResult(LexemType resultType, String value){
        this.resultType = resultType;
        this.setStringResult(value);
    }

    public OperationResult(LexemType resultType, int value){
        this.resultType = resultType;
        this.setIntResult(value);
    }

    public void setIntResult(int intResult) {
        this.intResult = intResult;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

    public int getIntResult() {
        return intResult;
    }

    public String getStringResult() {
        return stringResult;
    }

    public LexemType getResultType() {
        return resultType;
    }
}

