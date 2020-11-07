package com.inikitin.hikaribatch.repository;

import com.inikitin.hikaribatch.entity.ItemEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBatchRepository extends BatchRepository<ItemEntity, Long> {
}
