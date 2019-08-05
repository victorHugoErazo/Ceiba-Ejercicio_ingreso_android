package co.com.ceiba.mobile.pruebadeingreso.dto;

import java.util.List;

public class ResponsePositive implements IResp {

    private List<UserDTO> userResponse;
    private String typeResponse;

    public List<UserDTO> getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(List<UserDTO> userResponse) {
        this.userResponse = userResponse;
    }

    public void setTypeResponse(String typeResponse) {
        this.typeResponse = typeResponse;
    }

    @Override
    public String getTypeResponse() {
        return typeResponse;
    }
}
