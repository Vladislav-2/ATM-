package by.itstep.chernuk.repository;

import by.itstep.chernuk.domain.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BankRepo extends CrudRepository<Bank, Long> {

    Page<Bank> findAll(Pageable pageable);

    Bank findByBankName(String bankName);

    Bank getById(Long id);
}
