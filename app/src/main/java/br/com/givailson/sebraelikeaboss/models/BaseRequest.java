package br.com.givailson.sebraelikeaboss.models;

public class BaseRequest {
    private String data;
    private boolean result;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "data: " + getData()
                + "\nresult: " + isResult();
    }
}
