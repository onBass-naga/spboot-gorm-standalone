package com.example.myproject

import com.example.myproject.domain.Person
import com.example.myproject.service.PersonService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import java.text.MessageFormat

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.assertThat

/**
 * Created by naga on 2014/07/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:SpringBeans.groovy")
class PersonServiceTest {

    @Autowired
    PersonService sut

    @Test
    public void saveで登録できる() {

        Person p = new Person(familyName: 'Yagyuu', givenName: 'ZyuBei')
        assert !p.id

        sut.save(p)

        def actual = Person.findAll()[0].id

        assertThat(actual, is(not(nullValue())))
    }

    @Test
    public void 名前が11文字の場合は文字数チェックでエラーとなること() {

        Person p = new Person(familyName: 'Yagyuu', givenName: '12345678901')
        sut.validate(p)

        def error = p.errors.fieldErrors.first()
        def message = MessageFormat.format(
                error.defaultMessage,
                error.field,
                error.rejectedValue,
                10)

        assertThat(message,
                is('Property [givenName] with value [12345678901] exceeds the maximum size of [10]'))

    }
}
