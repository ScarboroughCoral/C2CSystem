package top.scarboroughcoral.c2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.scarboroughcoral.c2c.service.ImgService;

@RestController
@RequestMapping(value = "img")
public class ImgController {

    @Autowired
    private ImgService imgService;



}
