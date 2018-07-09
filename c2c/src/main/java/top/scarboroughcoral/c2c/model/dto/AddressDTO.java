package top.scarboroughcoral.c2c.model.dto;

public class AddressDTO {

    private String provice;
    private String city;
    private String district;

    public AddressDTO(){

    }

    public AddressDTO(String provice, String city, String district) {
        this.provice = provice;
        this.city = city;
        this.district = district;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
