/**
 * Created by naga on 2014/07/26.
 */
import org.apache.commons.dbcp.BasicDataSource
import org.codehaus.groovy.grails.orm.hibernate.events.PatchedDefaultFlushEventListener
import org.codehaus.groovy.grails.orm.hibernate.support.ClosureEventTriggeringInterceptor
import org.springframework.context.support.ResourceBundleMessageSource

beans {
    xmlns gorm:"http://grails.org/schema/gorm"
    xmlns context:"http://www.springframework.org/schema/context"
    xmlns tx:"http://www.springframework.org/schema/tx"

    context."annotation-config"()
    tx."annotation-driven"()

    messageSource(ResourceBundleMessageSource) {
        basename = "messages"
    }

    dataSource(BasicDataSource) {
        driverClassName = "org.h2.Driver"
        url = "jdbc:h2:mem:test"
        username = "sa"
        password = ""
    }

    gorm.sessionFactory("data-source-ref": "dataSource",
            "base-package": "com.example.myproject",
            "message-source-ref": "messageSource") {

        hibernateProperties = ["hibernate.hbm2ddl.auto": "create",
                               "hibernate.dialect": "org.hibernate.dialect.H2Dialect"]

        eventListeners = ["flush": new PatchedDefaultFlushEventListener(),
                          "pre-load": new ClosureEventTriggeringInterceptor(),
                          "post-load": new ClosureEventTriggeringInterceptor(),
                          "save": new ClosureEventTriggeringInterceptor(),
                          "save-update": new ClosureEventTriggeringInterceptor(),
                          "post-insert": new ClosureEventTriggeringInterceptor(),
                          "pre-update": new ClosureEventTriggeringInterceptor(),
                          "post-update": new ClosureEventTriggeringInterceptor(),
                          "pre-delete": new ClosureEventTriggeringInterceptor(),
                          "post-delete": new ClosureEventTriggeringInterceptor()]
    }

    context."component-scan"("base-package": "com.example.myproject")

}