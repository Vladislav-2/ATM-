package by.itstep.chernuk.service;

import by.itstep.chernuk.domain.Bank;
import by.itstep.chernuk.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    BankRepo bankRepo;

    public boolean saveBank(Bank bank){
        if (bankRepo.findByBankName(bank.getBankName()) == null
                && bank.getGlobalBalance() >= 1000000)           //1 000 000
        {
            bankRepo.save(bank);
            return true;
        } else {
            return false;
        }
    }

    public boolean blank(Bank bank){
        if (bank.getBankName() == null
                || bank.getGlobalBalance() == null){
            return false;
        } else {
            return true;
        }
    }

    public boolean outputMoney(Long quantity, Bank bank){
        if (quantity <= bank.getGlobalBalance()
                && quantity > 0){
            bank.outputCash(quantity);
            return true;
        } else {
            return false;
        }
    }

    public boolean inputMoney(Long quantity, Bank bank){
        if (quantity > 0) {
            bank.inputCash(quantity);
            return true;
        } else {
            return false;
        }
    }

    public void remove(Bank removeBank){
        removeBank.setDeleted(true);
        bankRepo.save(removeBank);
    }

    public void reaper(Bank reaperBank) {
        reaperBank.setDeleted(false);
        bankRepo.save(reaperBank);
    }

    public Page<Bank> getAll(Pageable pageable){
        return  bankRepo.findAll(pageable);
    }
}
