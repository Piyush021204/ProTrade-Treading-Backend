package com.piyush.service;

import com.piyush.model.Coin;
import com.piyush.model.User;
import com.piyush.model.WatchList;
import com.piyush.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class WatchListServiceImpl implements WatchListService {

    @Autowired
    private WatchListRepository watchListRepository;



    @Override
    public WatchList findUserWatchList(Long userId) throws Exception {
        WatchList watchList = watchListRepository.findByUserId(userId);
        if (watchList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WatchList not found for user id: " + userId);

        }

        return watchList;
    }

    @Override
    public WatchList createWatchList(User user) {
        WatchList watchList = new WatchList();
        watchList.setUser(user);

        return watchListRepository.save(watchList);
    }

    @Override
    public WatchList findById(Long id) throws Exception {
        Optional<WatchList> watchListOptional=watchListRepository.findById(id);
       if(watchListOptional.isEmpty()){
           throw new Exception("WatchList Not Found");
       }

        return watchListOptional.get();
    }

    @Override
    public Coin addItemToWatchList(Coin coin, User user) throws Exception {
        WatchList watchList =findUserWatchList(user.getId());

        if(watchList.getCoins().contains(coin)){
            watchList.getCoins().remove(coin);
        }
        else  watchList.getCoins().add(coin);
        watchListRepository.save(watchList);
         return coin;
    }
}
