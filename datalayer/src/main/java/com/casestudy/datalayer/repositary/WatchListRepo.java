package com.casestudy.datalayer.repositary;

import com.casestudy.datalayer.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepo extends JpaRepository<Watchlist, Long> {
}
