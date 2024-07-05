package com.casestudy.datalayer.repositary;

import com.casestudy.datalayer.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WatchListRepo extends JpaRepository<Watchlist, UUID> {
}
