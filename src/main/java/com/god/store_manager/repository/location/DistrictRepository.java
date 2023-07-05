package com.god.store_manager.repository.location;

import com.god.store_manager.model.location.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District,Long> {
    District getReferenceByIdAndProvinceId(long districtId, long provinceId);
    boolean existsByIdAndProvinceId(long districtId, long provinceId);
}
