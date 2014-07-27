package com.example.myproject.service

import com.example.myproject.domain.Person
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by naga on 2014/07/26.
 */
@Service("personService")
@Transactional
class PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonService.class)

    def findAll() {
        return Person.findAll()
    }

    def count() {
        return Person.count()
    }

    def save(Person person) {
        LOG.debug('★ #save(Person) was called ★')
        person.save()
    }

    def save(Collection<Person> persons) {
        LOG.debug('★ #save(Collection) was called ★ count: ' + persons.size())
        persons.each { person ->
            LOG.debug("★ person: ${person}")
            person.save()
        }
    }

    def validate(Person p) {
        p.validate()
    }
}
