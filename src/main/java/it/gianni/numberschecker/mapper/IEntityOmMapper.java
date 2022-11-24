package it.gianni.numberschecker.mapper;

import it.gianni.numberschecker.OM.SouthAfricanMobileNumberOM;
import it.gianni.numberschecker.entity.SouthAfricanMobileNumberEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEntityOmMapper {

    SouthAfricanMobileNumberEntity omToEntity(SouthAfricanMobileNumberOM om);

    SouthAfricanMobileNumberOM entityToOm(SouthAfricanMobileNumberEntity entity);

    List<SouthAfricanMobileNumberEntity> omsToEntities(List<SouthAfricanMobileNumberOM> oms);

    List<SouthAfricanMobileNumberOM> entitiesToOms(List<SouthAfricanMobileNumberEntity> entities);

}
