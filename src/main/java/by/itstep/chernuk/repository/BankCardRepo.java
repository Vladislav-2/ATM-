package by.itstep.chernuk.repository;

import by.itstep.chernuk.domain.BankCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public interface BankCardRepo extends CrudRepository<BankCard, Long> {

    BankCard findByCardUser(String cardUser);

    BankCard findByCardNumder(String string);

    BankCard findByPin(String pin);

    Page<BankCard> findAll(Pageable pageable);

    BankCard getById(Long id);
}