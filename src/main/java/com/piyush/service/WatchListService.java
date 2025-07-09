package com.piyush.service;

import com.piyush.model.Coin;
import com.piyush.model.User;
import com.piyush.model.WatchList;

public interface WatchListService {

    WatchList findUserWatchList(Long userId) throws Exception;

    WatchList createWatchList(User user);

    WatchList findById(Long id) throws Exception;

    Coin addItemToWatchList(Coin coin , User user) throws Exception;


}
