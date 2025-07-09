package com.piyush.service;

import com.piyush.model.Order;
import com.piyush.model.User;
import com.piyush.model.Wallet;

public interface WalletService {
    Wallet getUserWallet (User user);

    Wallet addBalence(Wallet wallet , Long money);

    Wallet findWalletById(Long id) throws Exception;

    Wallet walletToWalletTransfer(User sender, Wallet receivertWallet , Long amount) throws Exception;

    Wallet payOrderPayment(Order order, User user) throws Exception;
}
