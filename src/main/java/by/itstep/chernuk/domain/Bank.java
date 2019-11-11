package by.itstep.chernuk.domain;

import by.itstep.chernuk.domain.AbstractClasses.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Bank extends AbstractEntity {

    private String bankName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bank")
    private Set<ATM> atmSet;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bank")
    private Set<BankAccount> bankAccountSet;

    private Long globalBalance;

    public Bank() {
    }

    public Set<ATM> getAtmSet() {
        return atmSet;
    }

    public void setAtmSet(Set<ATM> atmSet) {
        this.atmSet = atmSet;
    }

    public Set<BankAccount> getBankAccountSet() {
        return bankAccountSet;
    }

    public void setBankAccountSet(Set<BankAccount> bankAccountSet) {
        this.bankAccountSet = bankAccountSet;
    }

    public Long getGlobalBalance() {
        return globalBalance;
    }

    public void setGlobalBalance(Long globalBalance) {
        this.globalBalance = globalBalance;
    }

    public void inputCash(Long quantity){
        this.globalBalance += quantity;
    }

    public void outputCash(Long quantity){
        this.globalBalance -= quantity;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
