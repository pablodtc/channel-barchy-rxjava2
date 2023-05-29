package com.barchy.rxjava.controller;

import com.barchy.rxjava.model.PersonModel;
import com.barchy.rxjava.service.spi.PersonService;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonController {

  private final PersonService service;

  @GetMapping("/get-person")
  public Single<PersonModel> getPerson (@RequestBody PersonModel person) {
    return service.getPerson(person);
  }
}
