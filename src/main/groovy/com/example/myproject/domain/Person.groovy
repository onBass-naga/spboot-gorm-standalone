package com.example.myproject.domain

import grails.persistence.Entity
import org.apache.commons.lang.builder.ToStringBuilder
import org.apache.commons.lang.builder.ToStringStyle

/**
 * Created by naga on 2014/07/26.
 */
@Entity
class Person {

    String familyName
    String givenName

    Date dateCreated
    Date lastUpdated

    static constraints = {
        familyName maxSize: 10, blank: false
        givenName maxSize: 10, blank: false

        dateCreated nullable:true
        lastUpdated nullable:true
    }

    @Override
    String toString() {
        ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}