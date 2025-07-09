package com.piyush.service;

import com.piyush.model.User;
import com.piyush.model.Withdrawal;

import java.util.List;

public interface WithdrawalService {

    Withdrawal requestWithdrawal(Long amount , User user);

    Withdrawal procedWithdrawal(Long withdrawalId,boolean accept) throws Exception;

    List<Withdrawal> getUserWithdrawalHistory(User user);

    List<Withdrawal> getAllWithdrawalRequest();

}
