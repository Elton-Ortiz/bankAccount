package com.smuteste.register.register;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smuteste.register.model.AccountUser;

@Repository
public interface RegisterRepository extends JpaRepository<AccountUser, Long> {

	Optional<AccountUser> findByFiscalNumber(String cpf);

	void deleteAllByFiscalNumber(String fiscalNumber);
	

}
