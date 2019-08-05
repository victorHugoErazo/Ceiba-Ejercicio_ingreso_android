package co.com.ceiba.mobile.pruebadeingreso.dto;

public class ResponseNegative implements IResp{

    private String response;

    public ResponseNegative(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String getTypeResponse() {
        return response;
    }
}
