package by.itstep.chernuk.domain;

import by.itstep.chernuk.domain.AbstractClasses.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "atm")
public class ATM extends AbstractEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    private Long cardId;

    private Long cash;

    private Long maxCash;

    private boolean isInseredCard;

    public ATM() {
    }

    public Long getBankId() {
        return bank.getId();
    }

    public void setBankId(Bank bank) {
        this.bank = bank;
    }

    public Long getCash() {
        return cash;
    }

    public void setCash(Long money) {
        this.cash = money;
    }

    public void inputCash(Long quantity){
        this.cash += quantity;
    }

    public void outputCash(Long quantity){
        this.cash -= quantity;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public boolean isInseredCard() {
        return isInseredCard;
    }

    public void setInseredCard(boolean inseredCard) {
        isInseredCard = inseredCard;
    }

    public Long getMaxCash() {
        return maxCash;
    }

    public void setMaxCash(Long max) {
        this.maxCash = max;
    }
}
