package com.server.vnnews.service;

import com.server.vnnews.dto.BodyItemDTO;
import com.server.vnnews.entity.BodyItem;
import com.server.vnnews.repository.BodyItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BodyItemService {
    @Autowired
    private BodyItemRepository bodyItemRepository;


    public List<BodyItem> getBodyItemByArticleId(Long articleId){
        return bodyItemRepository.findByArticleId(articleId);
    }

}
