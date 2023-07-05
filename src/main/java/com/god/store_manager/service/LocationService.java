package com.god.store_manager.service;

import com.god.store_manager.model.location.District;
import com.god.store_manager.model.location.Province;
import com.god.store_manager.model.location.Ward;
import com.god.store_manager.repository.location.DistrictRepository;
import com.god.store_manager.repository.location.ProvinceRepository;
import com.god.store_manager.repository.location.WardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;

    public List<Province> getAllProvinces(){
       return provinceRepository.findAll();
    }
    public Province getProvinceById(long provinceId){
        return provinceRepository.getReferenceById(provinceId);
    }
    public List<District> getAllDistricts(){
        return districtRepository.findAll();
    }
    public District getDistrictById(long districtId){
        return districtRepository.getReferenceById(districtId);
    }
    public District getDistrictByIdAndProvinceId(long districtId,long provinceId){
        return districtRepository.getReferenceByIdAndProvinceId(districtId,provinceId);
    }
    public List<Ward> getAllWards(){
        return wardRepository.findAll();
    }
    public Ward getWardById(long wardId){
        return wardRepository.getReferenceById(wardId);
    }

    public boolean validateLocation(long wardId, long districtId, long provinceId) {
        if(wardId!=0){
            return wardRepository.existsById(wardId);
        }
        else if(districtId!= 0){
            return districtRepository.existsByIdAndProvinceId(districtId,provinceId);
        }
        else if(provinceId !=0){
           return  provinceRepository.existsById(provinceId);
        }
        else {
            return true;
        }
    }

    public boolean addProvince(Province province) {
        try{
            provinceRepository.save(province);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
    public boolean addDistrict(District district) {
        try{
            districtRepository.save(district);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
    public boolean addWard(Ward ward) {
        try{
            wardRepository.save(ward);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
    public boolean updateProvince(Province province) {
        try{
            provinceRepository.save(province);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
    public boolean updateDistrict(District district) {
        try{
            districtRepository.save(district);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
    public boolean updateWard(Ward ward) {
        try{
            wardRepository.save(ward);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
    public boolean deleteProvince(long provinceId) {
        try{
            provinceRepository.deleteById(provinceId);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
    public boolean deleteDistrict(long districtId) {
        try{
            districtRepository.deleteById(districtId);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
    public boolean deleteWard(long wardId) {
        try{
            wardRepository.deleteById(wardId);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
}
