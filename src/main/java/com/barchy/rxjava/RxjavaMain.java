package com.barchy.rxjava;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

import java.util.Optional;
import java.util.stream.Stream;

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

    static Maybe<Person> helloWorld_3 (Person person) {

        if (person.getDocument().equals("00000000")){
           return Maybe.empty();
        }
        return Maybe.just(Person.builder()
                .document(person.getDocument())
                .name(person.getName())
                .build());
    }

    public static void main(String[] args) {

        /*helloWorld("Pablo")
                .subscribe(x -> System.out.println(x));*/

        helloWorld_2(null)
                .subscribe(x -> System.out.println(x));

        /*helloWorld_3(Person.builder()
                .document("00000001")
                .name("Pablo")
                .build())
                .filter(person -> person != null) //si el objeto no existe se crea
                .subscribe(x -> System.out.println("Vamos a crear el objeto: " + x));*/


    }
}
