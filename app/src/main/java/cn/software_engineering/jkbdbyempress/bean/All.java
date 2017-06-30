package cn.software_engineering.jkbdbyempress.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Asus on 2017/6/29.
 */

public class All implements Serializable{
    /**
     * error_code : 0
     * reason : ok
     * result : []
     */

    private int error_code;
    private String reason;
    private List<Quetion> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Quetion> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "All{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    public void setResult(List<Quetion> result) {
        this.result = result;
    }
}
