package io.stoks.repositories;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SampleDTO {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String externalId;

    private String number;

    private String name;

    private String alias;

    private String type;

    private BigDecimal balance;

    private String currency;

    private String status;

    private String customerId;

    private Boolean visible;

    private Boolean favorite;

    private OffsetDateTime createdOn;

    protected Instant lastUpdated;
}
