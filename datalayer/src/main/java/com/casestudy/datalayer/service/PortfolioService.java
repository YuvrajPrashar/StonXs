package com.casestudy.datalayer.service;
import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.dto.PortfolioDTO;
import com.casestudy.datalayer.entity.Portfolio;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.repositary.PortfolioRepo;
import com.casestudy.datalayer.repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepo portfolioRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MapperUtil mapperUtil;

    public PortfolioDTO getPortfolioByUserId(UUID id) {
        try {
            User user = userRepo.findById(id).orElse(null);
            if (user == null) {
                return null;
            }
            Portfolio portfolio= user.getPortfolio();
            return mapperUtil.mapPortfolioToPortfolioDTO(portfolio);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public PortfolioDTO getPortfolio(UUID id) {
        try {
            Portfolio portfolio = portfolioRepo.findById(id).orElse(null);
            if (portfolio == null) {
                return null;
            }
            return mapperUtil.mapPortfolioToPortfolioDTO(portfolio);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<PortfolioDTO> getAllPortfolios() {
        try {
            return mapperUtil.mapPortfolioListToPortfolioDTOList(portfolioRepo.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
