package com.server.vnnews.service;

import com.server.vnnews.dto.HistoryArticleDTO;
import com.server.vnnews.repository.HistoryArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryArticleService {

    @Autowired
    private HistoryArticleRepository historyArticleRepository;

    public List<HistoryArticleDTO> getArticlesByIdList(ArrayList<Long> idList) {
        return historyArticleRepository.findArticleByIds(idList);
    }
}

