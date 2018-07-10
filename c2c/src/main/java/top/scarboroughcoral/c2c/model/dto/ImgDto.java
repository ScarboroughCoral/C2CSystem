package top.scarboroughcoral.c2c.model.dto;

import java.io.File;

public class ImgDto {

    private Integer houseId;
    private File img;

    public ImgDto(Integer houseId, File img) {
        this.houseId = houseId;
        this.img = img;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }
}
