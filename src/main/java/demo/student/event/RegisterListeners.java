package demo.student.event;

import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
@RequiredArgsConstructor
public class RegisterListeners {

    private final EntityManagerFactory entityManagerFactory;
    private final PreAuditSaveListener preUpdateInsertListener;

    @PostConstruct
    public void registerListeners() {
        SessionFactoryImpl sessionFactoryImpl = entityManagerFactory.unwrap(SessionFactoryImpl.class);

        EventListenerRegistry registry = sessionFactoryImpl.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(preUpdateInsertListener);
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(preUpdateInsertListener);

    }
}
