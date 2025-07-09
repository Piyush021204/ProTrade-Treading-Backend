package com.piyush.controller;

import com.piyush.model.Coin;
import com.piyush.model.User;
import com.piyush.model.WatchList;
import com.piyush.service.CoinService;
import com.piyush.service.UserService;
import com.piyush.service.WatchListService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {

    @Autowired
    private  WatchListService watchListService;
    @Autowired
    private UserService userService;

    @Autowired
    private CoinService coinService;

    @GetMapping("/user")
    public ResponseEntity<WatchList>getUserWatchList(
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user=userService.findUserProfileByJwt(jwt);
        WatchList watchList = watchListService.findUserWatchList(user.getId());
        return ResponseEntity.ok(watchList);

    }


    @GetMapping("/{watchlistId}")
    public ResponseEntity<WatchList> getWatchListById(
            @PathVariable Long watchlistId)throws Exception{
        WatchList watchList = watchListService.findById(watchlistId);
        return ResponseEntity.ok(watchList);

    }


    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<Coin> addItemToWatchList(
            @RequestHeader("Authorization") String jwt,
            @PathVariable String coinId)throws Exception{
        User user=userService.findUserProfileByJwt(jwt);
        Coin coin = coinService.findById(coinId);
        Coin addedCoin = watchListService.addItemToWatchList(coin , user);
        return ResponseEntity.ok(addedCoin);
    }



}
