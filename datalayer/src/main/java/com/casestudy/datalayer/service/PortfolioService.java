package com.casestudy.datalayer.service;
import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.dto.PortfolioDTO;
import com.casestudy.datalayer.entity.Holdings;
import com.casestudy.datalayer.entity.Portfolio;
import com.casestudy.datalayer.entity.Stock;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.repositary.HoldingsRepo;
import com.casestudy.datalayer.repositary.PortfolioRepo;
import com.casestudy.datalayer.repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Autowired
    HoldingsRepo holdingsRepo;


    public PortfolioDTO getPortfolioByUserId(UUID id) {
        try {
            User user = userRepo.findById(id).orElse(null);
            if (user == null) {
                return null;
            }
            Portfolio portfolio= user.getPortfolio();
            PortfolioDTO portfolioDTO = mapperUtil.mapPortfolioToPortfolioDTO(portfolio);
            List<Stock> stocks = new ArrayList<>();
            List<Holdings> holdings = holdingsRepo.findByPortfolio(portfolio);


            int calculatedCurrentValue = 0;
            for (Holdings holding : holdings) {
                BigDecimal currentPrice = holding.getStocks().getCurrentPrice();
                int quantity = holding.getQuantity();
                calculatedCurrentValue += currentPrice.multiply(BigDecimal.valueOf(quantity)).intValue();
                if(holding.getStocks() != null && holding.getQuantity() > 0)
                stocks.add(holding.getStocks());
            }
            portfolioDTO.setStocks(mapperUtil.mapStockListToStockDTOList(stocks));

            portfolioDTO.setCurrentValue(calculatedCurrentValue);
            portfolioDTO.setPnl(calculatedCurrentValue - portfolioDTO.getInvestedvalue());
            return portfolioDTO;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public PortfolioDTO getPortfolioByPortfolioId(UUID id) {
        try {
            Portfolio portfolio = portfolioRepo.findById(id).orElse(null);
            if (portfolio == null) {
                return null;
            }

            PortfolioDTO portfolioDTO = mapperUtil.mapPortfolioToPortfolioDTO(portfolio);

            List<Stock> stocks = new ArrayList<>();
            List<Holdings> holdings = holdingsRepo.findByPortfolio(portfolio);


            int calculatedCurrentValue = 0;
            for (Holdings holding : holdings) {
                BigDecimal currentPrice = holding.getStocks().getCurrentPrice();
                int quantity = holding.getQuantity();
                calculatedCurrentValue += currentPrice.multiply(BigDecimal.valueOf(quantity)).intValue();
                if(holding.getStocks() != null && holding.getQuantity() > 0)
                stocks.add(holding.getStocks());
            }
            portfolioDTO.setStocks(mapperUtil.mapStockListToStockDTOList(stocks));

            portfolioDTO.setCurrentValue(calculatedCurrentValue);
            portfolioDTO.setPnl(calculatedCurrentValue - portfolioDTO.getInvestedvalue());
            return portfolioDTO;

        } catch (Exception e) {
            throw new RuntimeException("Error fetching portfolio data", e);
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
