package io.stoks.controllers;

import io.generated.stoks.api.SamplesApi;
import io.generated.stoks.model.AliasUpdate;
import io.generated.stoks.model.SampleResponse;
import io.generated.stoks.model.Samplereq;
import io.generated.stoks.model.Sampleres;
import io.stoks.services.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
public class SampleController implements SamplesApi {

    private final SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @Override
    public ResponseEntity<Sampleres> getSample(String sampleId) {
        log.info("Get Sample invoked with Id: {}", sampleId);
        return ResponseEntity.ok(sampleService.getSample(sampleId));
    }

    @Override
    public ResponseEntity<List<Sampleres>> getSamples() {
        log.info("Get Samples invoked");
        return ResponseEntity.ok(sampleService.getSamples());
    }

    @Override
    public ResponseEntity<SampleResponse> createSample(Samplereq request) {
        log.info("Received body {}", request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sampleService.createSample(request));
    }

    @Override
    public ResponseEntity<Void> updateSample(String sampleId, AliasUpdate aliasUpdate) {
        log.info("Received sampleId {} and body {}", sampleId, aliasUpdate);
        sampleService.updateSample(sampleId, aliasUpdate.getAlias());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<String> getFxRate(String destinationCurrency) {
        log.info("Received destinationCurrency {}", destinationCurrency);
        return ResponseEntity.ok("1.58");
    }


}
