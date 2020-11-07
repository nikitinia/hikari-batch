package com.inikitin.hikaribatch.repository;

import com.inikitin.hikaribatch.config.SpringContext;
import com.inikitin.hikaribatch.service.BatchExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Transactional(propagation = Propagation.NEVER)
public class BatchRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BatchRepository<T, ID> {
    private final JpaEntityInformation<T, ?> entityInfo;
    private final EntityManager entityManager;

    public BatchRepositoryImpl(JpaEntityInformation<T, ID> information, EntityManager entityManager) {
        super(information, entityManager);
        this.entityInfo = information;
        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> void saveInBatch(List<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("Entities cannot be null");
        }

        BatchExecutor batchExecutor = SpringContext.getBean(BatchExecutor.class);
        try {
            batchExecutor.saveInBatch(entities);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            log.error("saveInBatch({}) -> {}", entities.size(), ex);
        } catch (ExecutionException ex) {
            log.error("saveInBatch({}) -> {}", entities.size(), ex);
        }
    }
}
