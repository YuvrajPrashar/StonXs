package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.PortfolioDTO;
import com.casestudy.datalayer.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
class  PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    //find portfolio by user id
    @GetMapping("/auth/api-v1/user/{id}/portfolio")
    public ResponseEntity<PortfolioDTO> getPortfolioByUserId(@PathVariable("id") UUID id) {
        try {
            PortfolioDTO res = portfolioService.getPortfolioByUserId(id);
            if (res == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //find portfolio by portfolio id
    @GetMapping("/auth/api-v1/portfolio/{id}")
    public ResponseEntity<PortfolioDTO> getPortfolio(@PathVariable("id") UUID id) {
        try {
            PortfolioDTO res = portfolioService.getPortfolioByPortfolioId(id);
            if (res == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
