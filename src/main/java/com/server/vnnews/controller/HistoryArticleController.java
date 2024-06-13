package com.server.vnnews.controller;

import com.server.vnnews.dto.HistoryArticleDTO;
import com.server.vnnews.repository.HistoryArticleRepository;
import com.server.vnnews.service.HistoryArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/history-articles")
public class HistoryArticleController {

    @Autowired
    private HistoryArticleService historyArticleService;

    @PostMapping("/by-ids")
    public List<HistoryArticleDTO> getArticlesByIds(@RequestBody ArrayList<Long> idList) {
        return historyArticleService.getArticlesByIdList(idList);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
