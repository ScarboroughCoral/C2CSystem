package top.scarboroughcoral.c2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.scarboroughcoral.c2c.model.entity.HousesOfRenters;
import top.scarboroughcoral.c2c.model.entity.composite_id.HousesOfRentersId;

public interface House_Of_RenterRepository extends JpaRepository<HousesOfRenters,HousesOfRentersId> {


}
