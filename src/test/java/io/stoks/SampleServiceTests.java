package io.stoks;


import io.generated.stoks.model.Sampleres;
import io.stoks.mappers.SampleMapper;
import io.stoks.repositories.SampleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest()
public class SampleServiceTests {


    @Autowired
    private SampleMapper sampleMapper;


    @Test
    public void contextLoads() {
        assertNotNull(sampleMapper);
    }

    @Test
    public void testFormat() {
        String f = new DecimalFormat("#0.##").format(new BigDecimal("-2.9900000000"));
        System.out.println(f);
    }

    @Test
    public void givenDestinationToSource_whenMaps_thenCorrect() {
        SampleDTO sampleDTO = SampleDTO.builder()
                .name("name")
                .externalId("externalid-1")
                .alias("salva")
                .balance(new BigDecimal("090.00"))
                .currency("USD")
                .customerId("475142622")
                .number("12345678909")
                .status("ACTIVE")
                .type("CURRENT_ACCOUNT")
                .build();

        Sampleres source = sampleMapper.convertToModel(sampleDTO);

        Assertions.assertEquals(sampleDTO.getName(), source.getName());
        Assertions.assertEquals(sampleDTO.getBalance(), source.getBalance());
    }

}
