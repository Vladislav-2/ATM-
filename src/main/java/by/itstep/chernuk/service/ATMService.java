package by.itstep.chernuk.service;

import by.itstep.chernuk.domain.ATM;
import by.itstep.chernuk.domain.Bank;
import by.itstep.chernuk.domain.BankAccount;
import by.itstep.chernuk.domain.BankCard;
import by.itstep.chernuk.repository.ATMRepo;
import by.itstep.chernuk.repository.BankAccountRepo;
import by.itstep.chernuk.repository.BankCardRepo;
import by.itstep.chernuk.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ATMService {

    @Autowired
    ATMRepo atmRepo;

    @Autowired
    BankCardRepo bankCardRepo;

    @Autowired
    BankRepo bankRepo;

    @Autowired
    BankService bankService;

    @Autowired
    BankAccountRepo bankAccountRepo;

    public boolean saveATM(ATM atm){
        if (atm.getBankId() != null
                && atm.getCash() > 0
                && atm.getMaxCash() >= atm.getCash()
                && atm.isInseredCard() != true
                && atm.getCardId() == null)
        {
            atmRepo.save(atm);
            return true;
        } else {
            return false;
        }
    }

    public boolean outputMoney(Long quantity, ATM atm){
        Bank bank = bankRepo.getById(atm.getBankId());
        BankCard bankCard = bankCardRepo.getById(atm.getCardId());
        BankAccount bankAccount = bankAccountRepo.getById(bankCard.getBankAccountId());
        if (quantity <= atm.getCash()
                && atm.getCardId() != null
                && atm.isInseredCard() == true
                && quantity > 0
        ){
            bankAccount.outputCash(quantity);
            bank.outputCash(quantity);
            atm.outputCash(quantity);
            return true;
        } else {
            return false;
        }
    }

    public boolean inputMoney(Long quantity, ATM atm){
        Bank bank = bankRepo.getById(atm.getBankId());
        BankCard bankCard = bankCardRepo.getById(atm.getCardId());
        BankAccount bankAccount = bankAccountRepo.getById(bankCard.getBankAccountId());
        if ((quantity + atm.getCash()) <= atm.getMaxCash()
                && atm.getCardId() != null
                && atm.isInseredCard() == true
                && quantity > 0
        ){
            bankAccount.inputCash(quantity);
            bank.inputCash(quantity);
            atm.inputCash(quantity);
            return true;
        } else {
            return false;
        }
    }

    public void remove(ATM removeATM){
        removeATM.setDeleted(true);
        atmRepo.save(removeATM);
    }

    public void reaper(ATM reaperATM){
        reaperATM.setDeleted(false);
        atmRepo.save(reaperATM);
    }

    public void reload(ATM atm){
        atm.setCash(atm.getMaxCash());
    }

    public Page<ATM> getAll(Pageable pageable){
        return  atmRepo.findAll(pageable);
    }
}
