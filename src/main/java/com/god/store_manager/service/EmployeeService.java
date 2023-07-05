package com.god.store_manager.service;

import com.god.store_manager.DTO.PaginationRequest;
import com.god.store_manager.model.employee.Employee;
import com.god.store_manager.model.employee.WorkShift;
import com.god.store_manager.repository.auth.UserRepository;
import com.god.store_manager.repository.customer.CustomerRepository;
import com.god.store_manager.repository.employee.EmployeeRepository;
import com.god.store_manager.repository.employee.WorkShiftRepository;
import com.god.store_manager.util.Exception.EntityExistingException;
import com.god.store_manager.util.Exception.EntityNotExistingException;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final WorkShiftRepository workShiftRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public Page<Employee> getListEmployeeByPage(PaginationRequest paginationRequest){
        return employeeRepository.findAll(PaginationRequest.getPageRequest(paginationRequest,""));
    }
    public Optional<Employee> getEmployeeById(long employeeId){
        return employeeRepository.findById(employeeId);
    }
    public Optional<Employee> getEmployeeByUserId(long userId){
        return employeeRepository.findByUserId(userId);
    }
    public boolean deleteEmployeeById(long employeeId) throws IllegalArgumentException{
        Optional<Employee> employee = getEmployeeById(employeeId);
        if(employee.isPresent()){
            userRepository.deleteById(employee.get().getUserId());
            employeeRepository.deleteById(employeeId);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean addEmployee(Employee employee) throws EntityExistingException{
        long userId = employee.getUserId();
        if(userRepository.existsById(userId)){
            if(customerRepository.existsByUserId(userId))
                throw new EntityExistingException("UserId "+userId+" has been used for another customer");

            employeeRepository.save(employee);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean updateEmployee(Employee employee) throws EntityExistingException,EntityExistingException{
        if(employeeRepository.existsById(employee.getId())){
            long userId = employee.getUserId();

            if(customerRepository.existsByUserId(userId))
                throw new EntityExistingException("UserId "+userId+" has been used for another customer");
            if(employeeRepository.existsByUserId(userId))
                throw new EntityExistingException("UserId "+userId+" has been used for another employee");
            employeeRepository.save(employee);
            return true;
        }
        else {
            return  false;
        }
    }
    public Page<WorkShift> getListWorkShiftByEmployeeIdWithPaginate(
            @Nullable PaginationRequest paginationRequest,
            long employeeId){
        return workShiftRepository.findAllByEmployeeId(
                employeeId,
                PaginationRequest.getPageRequest(paginationRequest,"")
        );
    }
    public boolean addWorkShift(WorkShift workShift) throws EntityExistingException,EntityNotExistingException{
        Employee employee = workShift.getEmployee();
        if(employee!=null){
            if(workShiftRepository.existsById(workShift.getId())){
                throw new EntityExistingException("work shift id exiting");
            }
            else {
                workShiftRepository.save(workShift);
            }
            return  true;
        }
        else{
            throw new EntityNotExistingException("Employee not exit");
        }
    }
    public boolean updateWorkShift(WorkShift workShift) throws EntityNotExistingException{
        Employee employee = workShift.getEmployee();
        if(employee!=null){
            if(workShiftRepository.existsById(workShift.getId())){
                workShiftRepository.save(workShift);
            }
            else {
                throw new EntityNotExistingException("Work shift id not existing");
            }
            return  true;
        }
        else{
            throw new EntityNotExistingException("Employee is not existing");
        }
    }
    public boolean deleteWorkShiftById(long workShiftId){
        try {
             workShiftRepository.deleteById(workShiftId);
             return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Optional<WorkShift> getWorkShiftById(long workShiftId) {
        return workShiftRepository.findById(workShiftId);
    }

    public Page<WorkShift> getListWorkShiftByEmployeeAndPerxiodWithPagination(Date dateStart,
                                                                     Date dateEnd,
                                                                     long employeeId,
                                                                     @Nullable PaginationRequest paginationRequest
                                                                     ) {
        return workShiftRepository.findAllByEmployeeIdAndStartTimeBetween(
                employeeId,
                dateStart,
                dateEnd,
                PaginationRequest.getPageRequest(paginationRequest,""));
    }
}
