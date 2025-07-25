package com.piyush.service;

import com.piyush.domain.OrderType;
import com.piyush.model.Order;
import com.piyush.model.User;
import com.piyush.model.Wallet;
import com.piyush.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletServiceimpl implements  WalletService{

    @Autowired
   private WalletRepository walletRepository;


    @Override
    public Wallet getUserWallet(User user) {
        Wallet wallet =walletRepository.findByUserId(user.getId());
        if(wallet == null){
            wallet = new Wallet();
            wallet.setUser(user);
            wallet.setBalance(BigDecimal.ZERO);
            wallet = walletRepository.save(wallet);
        }
        return wallet;
    }

    @Override
    public Wallet addBalence(Wallet wallet, Long money) {
        BigDecimal balance=wallet.getBalance();
        BigDecimal newBalance=balance.add(BigDecimal.valueOf(money));

        wallet.setBalance(newBalance);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletById(Long id) throws Exception {
        Optional<Wallet>wallet = walletRepository.findById(id);
        if(wallet.isPresent()){
            return wallet.get();
        }
        throw new Exception("Wallet not found");
    }

    @Override
    public Wallet walletToWalletTransfer(User sender, Wallet receivertWallet, Long amount) throws Exception {
       Wallet senderWallet=getUserWallet(sender);
       if(senderWallet.getBalance().compareTo(BigDecimal.valueOf(amount))<0){
           throw new Exception("Insufficient Balance...");
       }
       BigDecimal senderBalance=senderWallet.getBalance().subtract(BigDecimal.valueOf(amount));

       senderWallet.setBalance(senderBalance);
       walletRepository.save(senderWallet);

       BigDecimal receiverBalance=receivertWallet.getBalance().add(BigDecimal.valueOf(amount));
       receivertWallet.setBalance(receiverBalance);
       walletRepository.save(receivertWallet);
        return senderWallet;
    }

    @Override
    public Wallet payOrderPayment(Order order ,User user) throws Exception {
        Wallet wallet = getUserWallet(user);

        if(order.getOrderType().equals(OrderType.BUY)){
            BigDecimal newBalance=wallet.getBalance().subtract(order.getPrice());
            if(newBalance.compareTo(order.getPrice())<0){
                throw new Exception("Insufficient funds for this Transaction");
            }
            wallet.setBalance(newBalance);
        }
        else {
            BigDecimal newBalance=wallet.getBalance().add(order.getPrice());
            wallet.setBalance(newBalance);
        }
        walletRepository.save(wallet);
        return wallet;
    }
}
