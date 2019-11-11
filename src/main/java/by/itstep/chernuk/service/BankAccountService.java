package by.itstep.chernuk.service;

import by.itstep.chernuk.domain.BankAccount;
import by.itstep.chernuk.repository.BankAccountRepo;
import by.itstep.chernuk.repository.BankCardRepo;
import by.itstep.chernuk.repository.BankRepo;
import by.itstep.chernuk.repository.BankUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepo bankAccountRepo;

    @Autowired
    BankRepo bankRepo;

    @Autowired
    BankUserRepo bankUserRepo;

    @Autowired
    BankCardRepo bankCardRepo;

    public boolean saveBankAccount(BankAccount bankAccount){
        bankAccountRepo.save(bankAccount);
        return true;
    }

    public boolean blank(BankAccount bankAccount){
        if (bankRepo.getById(bankAccount.getBankId()) != null
                && bankUserRepo.getById(bankAccount.getBankUserId()) != null
                && bankCardRepo.getById(bankAccount.getBankCardId()) != null
                && bankAccount.getBalance() >= 0
        ){
            return true;
        } else {
            return false;
        }
    }

    public Page<BankAccount> getAll(Pageable pageable){
        return  bankAccountRepo.findAll(pageable);
    }
}
