package by.itstep.chernuk.repository;

import by.itstep.chernuk.domain.BankUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BankUserRepo extends CrudRepository<BankUser,Long> {

    Page findAll(Pageable pageable);

    BankUser findByFirstName(String firstName);

    BankUser findBySurname(String surname);

    BankUser findByPatronymicName(String patronymicName);

    BankUser findByEmail(String email);

    BankUser getById(Long id);
}
