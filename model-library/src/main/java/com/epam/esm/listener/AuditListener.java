package com.epam.esm.listener;

import com.epam.esm.entity.AbstractEntity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class AuditListener {
    private static final String PRE_PERSIST_OPERATION = "pre persist";
    private static final String POST_PERSIST_OPERATION = "post persist";
    private static final String PRE_UPDATE_OPERATION = "pre update";
    private static final String POST_UPDATE_OPERATION = "post update";
    private static final String PRE_REMOVE_OPERATION = "pre remove";
    private static final String POST_REMOVE_OPERATION = "post remove";
    private static final String ID = "id";

    private final Logger logger = LoggerFactory.getLogger(AuditListener.class);

    @PrePersist
    private void prePersistAudit(AbstractEntity abstractEntity) {
        logger.debug("{}", getMessage(PRE_PERSIST_OPERATION, abstractEntity));
    }

    @PostPersist
    private void postPersistAudit(AbstractEntity abstractEntity) {
        logger.debug("{}", getMessage(POST_PERSIST_OPERATION, abstractEntity));
    }

    @PreUpdate
    private void preUpdateAudit(AbstractEntity abstractEntity) {
        logger.debug("{}", getMessage(PRE_UPDATE_OPERATION, abstractEntity));
    }

    @PostUpdate
    private void postUpdateAudit(AbstractEntity abstractEntity) {
        logger.debug("{}", getMessage(POST_UPDATE_OPERATION, abstractEntity));
    }

    @PreRemove
    private void preRemoveAudit(AbstractEntity abstractEntity) {
        logger.debug("{}", getMessage(PRE_REMOVE_OPERATION, abstractEntity));
    }

    @PostRemove
    private void postRemoveAudit(AbstractEntity abstractEntity) {
        logger.debug("{}", getMessage(POST_REMOVE_OPERATION, abstractEntity));
    }

    private String getMessage(String operation, AbstractEntity abstractEntity) {
        return String.format("%s, %s, %s: %s, %tc", operation, abstractEntity.getClass(), ID, abstractEntity.getId(), new Date());
    }
}
