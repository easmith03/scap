package demo.student.event;

import demo.student.domain.AuditableEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * Listener for Entity loads.
 */
@Slf4j
@Component
public class PreAuditSaveListener implements PreUpdateEventListener, PreInsertEventListener {

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        EntityPersister persister = event.getPersister();
        if (event.getEntity() instanceof AuditableEntity) {
            auditUpdate((AuditableEntity) event.getEntity(), event.getState(), event.getPersister());
        }
        return false;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        EntityPersister persister = event.getPersister();
        if (event.getEntity() instanceof AuditableEntity) {
            auditUpdate((AuditableEntity) event.getEntity(), event.getState(), event.getPersister());
        }
        return false;
    }

    private void auditUpdate(AuditableEntity entity, Object[] state, EntityPersister entityPersister) {
        for (int idx = 0; idx < entityPersister.getPropertyNames().length; idx++) {
            if ("lastModified".equals(entityPersister.getPropertyNames()[idx])) {
                entity.setLastModified(Timestamp.valueOf(LocalDateTime.now()));
                state[idx] = entity.getLastModified();
                break;
            }
        }
    }

}
