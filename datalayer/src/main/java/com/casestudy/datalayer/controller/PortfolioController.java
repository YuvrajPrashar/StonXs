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

    @GetMapping("/portfolio/{id}")
    public PortfolioDTO getPortfolio(@PathVariable("id") UUID id) {
        return portfolioService.getPortfolio(id);
    }

    @GetMapping("/user/{id}/portfolio")
    public PortfolioDTO getPortfolioByUserId(@PathVariable("id") UUID id) {
        return portfolioService.getPortfolioByUserId(id);
    }
}
