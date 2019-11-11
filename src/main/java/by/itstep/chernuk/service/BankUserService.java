package by.itstep.chernuk.service;

import by.itstep.chernuk.domain.BankUser;
import by.itstep.chernuk.repository.BankUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankUserService {

    @Autowired
    BankUserRepo bankUserRepo;

    public boolean saveCardUser(BankUser bankUser){
        if ((bankUserRepo.findByFirstName(bankUser.getFirstName()) == null
                || bankUserRepo.findBySurname(bankUser.getSurname()) == null
                || bankUserRepo.findByPatronymicName(bankUser.getPatronymicName()) == null)
                && bankUserRepo.findByEmail(bankUser.getEmail()) == null){
            bankUserRepo.save(bankUser);
            return true;
        } else {
            return false;
        }
    }

    public boolean blank(BankUser bankUser){
        if (bankUser.getFirstName() == ""
                || bankUser.getSurname() == ""
                || bankUser.getPatronymicName() == ""
                || bankUser.getEmail() == ""
        ){
            return false;
        } else {
            return true;
        }
    }

    public void remove(BankUser removeBankUser){
        removeBankUser.setDeleted(true);
        bankUserRepo.save(removeBankUser);
    }

    public void reaper(BankUser reaperBankUser) {
        reaperBankUser.setDeleted(false);
        bankUserRepo.save(reaperBankUser);
    }

    public Page<BankUser> getAll(Pageable pageable){
        return  bankUserRepo.findAll(pageable);
    }
}
