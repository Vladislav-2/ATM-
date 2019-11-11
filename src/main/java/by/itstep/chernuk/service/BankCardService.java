package by.itstep.chernuk.service;

import by.itstep.chernuk.domain.BankCard;
import by.itstep.chernuk.repository.BankCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankCardService {

    @Autowired
    BankCardRepo bankCardRepo;

    public boolean saveBankCard(BankCard bankCard){
        if ( bankCardRepo.findByPin(bankCard.getPin()) == null
                && bankCardRepo.findByCardNumder(bankCard.getCardNumder()) == null
                && bankCard.getCardNumder() != null
                && bankCard.getPin() != null
                && bankCard.getCardUser() != null
                && bankCard.getUsageTime() != null
                && bankCard.getBankAccountId() != null){
            bankCardRepo.save(bankCard);
            return true;
        } else {
            return false;
        }
    }

    public Page<BankCard> getAll(Pageable pageable){
        return bankCardRepo.findAll(pageable);
    }

    public void remove(BankCard removeCard){
        removeCard.setDeleted(true);
        bankCardRepo.save(removeCard);
    }

    public void reaper(BankCard reaperCard){
        reaperCard.setDeleted(false);
        bankCardRepo.save(reaperCard);
    }

    public void block(BankCard blockCard){
        blockCard.setBlocked(true);
        bankCardRepo.save(blockCard);
    }

    public void unBlock(BankCard unBlockCard){
        unBlockCard.setBlocked(false);
        bankCardRepo.save(unBlockCard);
    }
}
