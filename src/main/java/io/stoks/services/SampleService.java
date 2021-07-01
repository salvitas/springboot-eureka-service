package io.stoks.services;

import io.generated.stoks.model.SampleResponse;
import io.generated.stoks.model.Samplereq;
import io.generated.stoks.model.Sampleres;
import io.stoks.mappers.SampleMapper;
import io.stoks.repositories.SampleDTO;
import io.stoks.repositories.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SampleService {

    protected SampleMapper sampleMapper;
    protected SampleRepository sampleRepository;

    @Autowired
    public SampleService(SampleMapper sampleMapper, SampleRepository sampleRepository) {
        this.sampleMapper = sampleMapper;
        this.sampleRepository = sampleRepository;
    }

    public SampleResponse createSample(Samplereq request) {
        SampleDTO sampleDTO = sampleRepository.save(sampleMapper.convertToDTO(request));
        SampleResponse sampleResponse = new SampleResponse();
        sampleResponse.setId(sampleDTO.getId());
        return sampleResponse;
    }

    public void updateSample(String sampleId, String alias) {
        SampleDTO sampleDTO = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new EntityNotFoundException(sampleId));

        sampleDTO.setAlias(alias);
        sampleRepository.save(sampleDTO);
    }

    public List<Sampleres> getSamples() {
        return sampleRepository.findAll(Pageable.unpaged())
                .stream()
                .map(sampleMapper::convertToModel)
                .collect(Collectors.toList());
    }

    public Sampleres getSample(String itemId) {
        return sampleRepository.findById(itemId)
                .map(sampleMapper::convertToModel)
                .orElseThrow(() -> new EntityNotFoundException(itemId));
    }
}
