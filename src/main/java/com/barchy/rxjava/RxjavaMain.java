package com.barchy.rxjava;

import com.barchy.rxjava.errors.model.GeneralError;
import com.barchy.rxjava.model.PersonModel;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class RxjavaMain {

    static Single<String> helloWorld(String greeting){

        return Single.just(greeting);
    }

    static Single<String> helloWorld_2(String greeting) {

        var saludoPorDefecto = Optional.of(greeting)
                .filter(g -> g != null)
                .map(g -> g.toUpperCase())
                .orElse("blabla");

        return Single.just(saludoPorDefecto);

    }

    static Maybe<PersonModel> helloWorld_3 (PersonModel person) {

        if (person.getDocument().equals("00000000")){
           return Maybe.empty();
        }
        return Maybe.just(PersonModel.builder()
                .document(person.getDocument())
                .name(person.getName())
                .build());
    }

    static Single<PersonModel> getPerson (PersonModel person) {
        if (person.getDocument().equals("11111111")) {
            return Single.error(new GeneralError("TL0001", "DNI"));
        }
        else {
            return Single.just(person);
        }

    }

    public static void main(String[] args) {

        /*helloWorld("Pablo")
                .subscribe(x -> System.out.println(x));*/

        /*helloWorld_2(null)
                .subscribe(x -> System.out.println(x));*/

        /*helloWorld_3(Person.builder()
                .document("00000001")
                .name("Pablo")
                .build())
                .filter(person -> person != null) //si el objeto no existe se crea
                .subscribe(x -> System.out.println("Vamos a crear el objeto: " + x));*/
        getPerson(PersonModel.builder()
            .document("11111111")
            .name("xxx")
            .build())
            .onErrorResumeNext(error -> {
                //log.error(">>>>> {}", error);
                if (error.getMessage().equals("DNI")) {
                    return Single.fromObservable(Observable.empty());
                } else {
                    return Single.error(new RuntimeException("xxx"));
                }
            })
            .subscribe(x -> log.error(String.valueOf(x)));

    }
}
