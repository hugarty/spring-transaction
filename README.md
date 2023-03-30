# Spring-transaction

Projeto **R&D** explica de maneira detalhada o funcionamento da Annotation **@Transactional** dentro do contexto do Spring Boot.

A anotação é do Spring *org.springframework.transaction.annotation.Transactional*

Pesquisa feita com a tecnologia **Repository** especificamente com a classe *org.springframework.data.repository.CrudRepository*


As operações são:

* [FindAndChange](https://github.com/hugarty/spring-transaction/blob/main/src/main/java/com/example/demo/model/service/FindAndChangeService.java)
* [Find](https://github.com/hugarty/spring-transaction/blob/main/src/main/java/com/example/demo/model/service/FindService.java)
* [Save](https://github.com/hugarty/spring-transaction/blob/main/src/main/java/com/example/demo/model/service/SaveService.java)
* [Update](https://github.com/hugarty/spring-transaction/blob/main/src/main/java/com/example/demo/model/service/UpdateService.java)
* [Delete](https://github.com/hugarty/spring-transaction/blob/main/src/main/java/com/example/demo/model/service/DeleteService.java)

Também testo [innertransaction](https://github.com/hugarty/spring-transaction/tree/main/src/main/java/com/example/demo/model/service/innertransaction) e [outertransaction](https://github.com/hugarty/spring-transaction/tree/main/src/main/java/com/example/demo/model/service/outertransaction)
