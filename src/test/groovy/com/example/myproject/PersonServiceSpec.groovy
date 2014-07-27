package com.example.myproject

import com.example.myproject.domain.Person
import com.example.myproject.service.PersonService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import spock.lang.Specification

/**
 * Created by naga on 2014/07/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:SpringBeans.groovy")
class PersonServiceSpec extends Specification {

    @Autowired
    PersonService personService

    @Test
    def "Speckでテストを行う"(){

        when: "app is run without arguments"
        personService.save(new Person(familyName: 'Yagyuu', givenName: 'ZyuBei'))

        then:
        Person.findAll().size() > 0
    }
}
