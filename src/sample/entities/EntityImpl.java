package sample.entities;

public class EntityImpl implements Entity {
    protected String date;
    protected String result;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public Double getResultDoub() {
        return Double.parseDouble(result);
    }

    public void setResult(String result) {
        this.result = result;
    }
}
