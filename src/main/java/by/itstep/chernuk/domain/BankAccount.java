package by.itstep.chernuk.domain;

import by.itstep.chernuk.domain.AbstractClasses.AbstractEntity;
import by.itstep.chernuk.repository.BankRepo;
import by.itstep.chernuk.repository.BankUserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class BankAccount extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "bankAccount")
    private BankCard bankCard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_user_id")
    private BankUser bankUser;

    private Long balance;

    public BankAccount() {
    }

    public Long getBankId() {
        return bank.getId();
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Long getBankCardId() {
        return bankCard.getId();
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public Long getBankUserId() {
        return bankUser.getId();
    }

    public void setBankUser(BankUser bankUser) {
        this.bankUser = bankUser;
    }

    public void inputCash(Long cash) {
       balance += cash;
    }

    public void outputCash(Long cash){
        if (balance - cash >= 0) {
            balance -= cash;
        }
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
