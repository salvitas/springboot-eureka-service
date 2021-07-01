package io.stoks.mappers;


import io.generated.stoks.model.Samplereq;
import io.generated.stoks.model.Sampleres;
import io.stoks.repositories.SampleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SampleMapper {

    @Mapping(target = "balance", source = "balance", numberFormat = "#0.##")
    @Mapping(target = "createdOn", source = "createdOn", dateFormat = "yyyy-MM-dd'T'hh:mm:ss")
    Sampleres convertToModel(SampleDTO destination);


    @Mapping(target = "alias", constant = "TEST")
    SampleDTO convertToDTO(Samplereq request);
}
