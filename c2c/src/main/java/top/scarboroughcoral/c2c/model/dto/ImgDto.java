package top.scarboroughcoral.c2c.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public class ImgDto {

    private Integer houseId;
    private MultipartFile imgList;

    public ImgDto(){

    }

    public ImgDto(Integer houseId, MultipartFile imgList) {
        this.houseId = houseId;
        this.imgList = imgList;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public MultipartFile getImgList() {
        return imgList;
    }

    public void setImgList(MultipartFile imgList) {
        this.imgList = imgList;
    }
}
