package com.server.vnnews.controller;

import com.server.vnnews.dto.BodyItemDTO;
import com.server.vnnews.entity.BodyItem;
import com.server.vnnews.service.BodyItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class BodyItemController {
    @Autowired
    private BodyItemService bodyItemService;
    @GetMapping("/test-get-body-item/{articleId}")
    public List<BodyItem> fetchController(@PathVariable Long articleId){
        return bodyItemService.getBodyItemByArticleId(articleId);
    }
}
