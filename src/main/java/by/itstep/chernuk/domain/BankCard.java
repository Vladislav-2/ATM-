package by.itstep.chernuk.domain;

import by.itstep.chernuk.domain.AbstractClasses.AbstractEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@Entity
public class BankCard extends AbstractEntity {

    @Column(updatable = false, unique = true)
    @Pattern(regexp = "[0-9]{4}")
    private String pin;

    @NotBlank
    @Column(updatable = false)
    private String cardUser;

    @Column(updatable = false, unique = true)
    @Pattern(regexp = "[0-9]{16}")
    private String cardNumder;

    @NotNull
    @Column(updatable = false)
    private Date usageTime;

    @OneToOne
    private BankAccount bankAccount;

    public BankCard() {
    }

    private boolean isBlocked = false;

    public String getPin() {
        return pin;
    }

    public String getCardUser() {
        return cardUser;
    }

    public String getCardNumder() {
        return cardNumder;
    }

    public Date getUsageTime() {
        return usageTime;
    }

    public String isBlockedString() {
        return isBlocked ? "true" : "false";
    }

    public boolean isBlocked(){
        return isBlocked;
    }

//    public Long getCash() {
//        return bankAccount.getBalance();
//    }

    public Long getBankAccountId() {
        return bankAccount.getBankId();
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
