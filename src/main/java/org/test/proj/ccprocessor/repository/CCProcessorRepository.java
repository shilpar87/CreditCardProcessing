package org.test.proj.ccprocessor.repository;

import org.springframework.data.repository.CrudRepository;
import org.test.proj.ccprocessor.model.CreditCardInfoData;

public interface CCProcessorRepository extends CrudRepository<CreditCardInfoData, Integer> {

}