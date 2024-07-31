package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.PortfolioDTO;
import com.casestudy.datalayer.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
class  PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/auth/api-v1/user/{id}/portfolio")
    public PortfolioDTO getPortfolioByUserId(@PathVariable("id") UUID id) {
        try {
            return portfolioService.getPortfolioByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/auth/api-v1/portfolio/{id}")
    public PortfolioDTO getPortfolio(@PathVariable("id") UUID id) {
        try {
            return portfolioService.getPortfolioByPortfolioId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
