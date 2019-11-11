package by.itstep.chernuk.repository;

import by.itstep.chernuk.domain.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BankAccountRepo extends CrudRepository<BankAccount, Long> {

    Page<BankAccount> findAll(Pageable pageable);

    BankAccount getById(Long id);
}
