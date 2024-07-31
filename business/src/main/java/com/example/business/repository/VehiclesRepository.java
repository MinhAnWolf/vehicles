package com.example.business.repository;

import com.example.business.dto.CollectVehiclesDTO;
import com.example.business.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiclesRepository extends JpaRepository<Vehicles, Long> {
    @Query(value = "SELECT o.id, o.make, 0.mode, o.type, o.year, o.id_user, u.associated_cost, u.description, u.service_date FROM vehicles o INNER JOIN maintenance_records u\n" +
            "ON o.id = u.vehicles_id WHERE id_user = :idUser", nativeQuery = true)
    List<CollectVehiclesDTO> getCollectVehicles(@Param("idUser") Long idUser);

}
