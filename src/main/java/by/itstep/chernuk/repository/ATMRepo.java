package by.itstep.chernuk.repository;

import by.itstep.chernuk.domain.ATM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ATMRepo extends CrudRepository<ATM, Long> {
    Page<ATM> findAll(Pageable pageable);
}
