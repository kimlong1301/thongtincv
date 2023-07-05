package com.god.store_manager.repository.employee;

import com.god.store_manager.model.employee.WorkShift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface WorkShiftRepository extends JpaRepository<WorkShift,Long> {
    public WorkShift findByEmployeeId(long employeeId);
    Page<WorkShift> findAllByEmployeeId(long employeeId, Pageable pageable);
    Page<WorkShift> findAllByEmployeeIdAndStartTimeBetween(
            long employeeId,
            Date dateStart,
            Date dateEnd,
            Pageable pageable);
}
