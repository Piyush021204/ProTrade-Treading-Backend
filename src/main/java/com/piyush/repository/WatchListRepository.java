package com.piyush.repository;

import com.piyush.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchList ,Long> {

    WatchList findByUserId(Long userId);



}
