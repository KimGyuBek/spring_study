package com.example.testproject.data.entity.listener;

import com.example.testproject.data.entity.Listener;
import java.util.logging.Logger;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class CustomListener {

    private final Logger LOGGER = Logger.getGlobal();

    @PostLoad
    public void postLoad(Listener entity) {
        LOGGER.info("[postLoad] called!");
    }

    @PrePersist
    public void prePersist(Listener entity) {
        LOGGER.info("[prePersist] called!");
    }

    @PostPersist
    public void postPersist(Listener entity) {
        LOGGER.info("[postPersist] called!");
    }

    @PreUpdate
    public void preUpdate(Listener entity) {
        LOGGER.info("[preUpdate] called!");
    }

    @PostUpdate
    public void postUpdate(Listener entity) {
        LOGGER.info("[postUpdate] called!");
    }

    @PreRemove
    public void preRemove(Listener entity) {
        LOGGER.info("[preRemove] called!");
    }

    @PostRemove
    public void postRemove(Listener entity) {
        LOGGER.info("[postRemove] called!");
    }
}
