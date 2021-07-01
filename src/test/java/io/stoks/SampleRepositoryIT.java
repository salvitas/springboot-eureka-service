package io.stoks;

import io.stoks.repositories.SampleDTO;
import io.stoks.repositories.SampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;


@Slf4j
@SpringBootTest
public class SampleRepositoryIT {

    @Autowired
    private SampleRepository sampleRepository;

    private boolean tableWasCreatedForTest;

    @BeforeEach
    public void init() throws Exception {

    }

    @Test
    public void testAccountsTable() {
        SampleDTO acc1 = SampleDTO.builder().name("account1").externalId("core-1").alias("salva").balance(new BigDecimal("123.34")).build();
        sampleRepository.save(acc1);

        SampleDTO acc2 = SampleDTO.builder().name("account2").externalId("core-2").alias("juan").balance(new BigDecimal("009.00")).build();
        sampleRepository.save(acc2);

        Page<SampleDTO> result = sampleRepository.findByAlias("salva", Pageable.unpaged());
//        assertEquals(result.stream().count(), 1L);
        assertThat(result, hasItem(acc1));
        log.info("Found in table: {}", result.get().findFirst());
    }

    @AfterEach
    public void destroy() throws Exception {
        if (tableWasCreatedForTest) {

        }
    }
}
