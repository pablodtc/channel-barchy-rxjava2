package com.barchy.rxjava.service.spi;

import com.barchy.rxjava.model.PersonModel;
import io.reactivex.Single;

public interface PersonService {
  Single<PersonModel> getPerson(PersonModel person);
}
