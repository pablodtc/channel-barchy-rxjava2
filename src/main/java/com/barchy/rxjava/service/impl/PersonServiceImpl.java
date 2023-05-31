package com.barchy.rxjava.service.impl;

import com.barchy.rxjava.errors.model.GeneralError;
import com.barchy.rxjava.model.PersonModel;
import com.barchy.rxjava.service.spi.PersonService;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
  @Override
  public Single<PersonModel> getPerson(PersonModel person) {

    PersonModel personResponse = PersonModel.builder()
        .document(person.getDocument())
        .name(person.getName())
        .build();


    return Single.just(personResponse)
        .flatMap(p -> validatePerson(p))
            .onErrorReturn(error -> new PersonModel("11111111","Pedroooo"));
        //.onErrorResumeNext(Single.just(new PersonModel("11111111","Pedro")));

  }

  private Single<PersonModel> validatePerson (PersonModel personModel) {
    if (personModel.getDocument().equals("00000000")) {
        log.info(">>> personModel {}", personModel);
      return Single.error(new RuntimeException("E001"));
      //throw new GeneralError("E001", "DNI BLOCK");
    }
    return Single.just(personModel);
  }
}
