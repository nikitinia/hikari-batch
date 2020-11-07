package com.inikitin.hikaribatch;

import com.inikitin.hikaribatch.entity.ItemEntity;
import com.inikitin.hikaribatch.repository.ItemBatchRepository;
import com.inikitin.hikaribatch.repository.ItemRepository;
import com.inikitin.hikaribatch.service.HikariBatchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class HikariBatchApplicationTests {

	@Autowired
	private HikariBatchService hikariBatchService;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemBatchRepository itemBatchRepository;


	@Test
	void contextLoads() {
		List<ItemEntity> items = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			ItemEntity item = new ItemEntity("title " + i);
			items.add(item);
		}

		LocalTime startTime = LocalDateTime.now().toLocalTime();
		itemRepository.saveAll(items);
		log.info("Batch time: " + ChronoUnit.MILLIS.between(LocalDateTime.now().toLocalTime(), startTime));
		startTime = LocalDateTime.now().toLocalTime();
		itemBatchRepository.saveInBatch(items);
		log.info("Custom batch time: " + ChronoUnit.MILLIS.between(LocalDateTime.now().toLocalTime(), startTime));
	}

}
