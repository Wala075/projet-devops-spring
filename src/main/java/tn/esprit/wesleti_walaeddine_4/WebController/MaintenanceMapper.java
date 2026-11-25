package tn.esprit.wesleti_walaeddine_4.WebController;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.wesleti_walaeddine_4.Domaine.Maintenance;

// componentModel = "spring" : MapStruct genere MaintenanceMapperImpl annote @Component, injectable dans le service
@Mapper(componentModel = "spring")
public interface MaintenanceMapper {

    @Mapping(target = "idMaintenance", source = "idMaintenance")
    @Mapping(target = "idVehicule", source = "vehicule.idVehicule")
    MaintenanceDTO toDto(Maintenance maintenance);
}
