package com.example.myproject

import com.example.myproject.domain.Person
import com.example.myproject.service.PersonService
import grails.spring.BeanBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext

/**
 * Created by naga on 2014/07/26.
 */
class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class)

    public static void main(String[] args) {

        BeanBuilder beanBuilder = new BeanBuilder()
        beanBuilder.loadBeans("classpath:SpringBeans.groovy")

        ApplicationContext context = beanBuilder.createApplicationContext()
        PersonService personService = context.getBean("personService") as PersonService

        personService.save([
                new Person(familyName: 'Yamada', givenName: 'Taro'),
                new Person(familyName: 'Asakura', givenName: 'Hanako')
        ])

        LOG.info("★persons count: ${personService.count()}")
        LOG.info ("★persons: ${personService.findAll()}")

        Person person = personService.findAll()[0]
        person.givenName = "Koziro"
        personService.save(person)

        LOG.info ("★persons: ${personService.findAll()}")
    }

}
