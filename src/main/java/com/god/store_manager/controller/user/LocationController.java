package com.god.store_manager.controller.user;

import com.god.store_manager.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/location")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/provinces")
    public ResponseEntity getListProvince(){
        try{
            return ResponseEntity.ok(locationService.getAllProvinces());
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Something went wrong, please try again later");
        }

    }
    @GetMapping("/districts")
    public ResponseEntity getAllDistricts(){
        try{
            return ResponseEntity.ok(locationService.getAllDistricts());
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Something went wrong, please try again later");
        }

    }
    @GetMapping("/districts/from-province")
    public ResponseEntity getAllDistrictsFromProvinceId(@RequestParam long provinceId){
        try{
            return ResponseEntity.ok(locationService.getProvinceById(provinceId).getDistricts());
        }
        catch (Exception e){
            return ResponseEntity.status(404).body("Something went wrong, please check the province id");
        }
    }
    @GetMapping("/wards")
    public ResponseEntity getListWard(){
        try{
            return ResponseEntity.ok(locationService.getAllWards());
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Something went wrong, please try again later");
        }

    }
    @GetMapping("/wards/from-district")
    public ResponseEntity getAllWardFromDistrictId(@RequestParam long districtId){
        try{
            return ResponseEntity.ok(locationService.getDistrictById(districtId).getWards());
        }
        catch (Exception e){
            return ResponseEntity.status(404).body("Something went wrong, please check the district id");
        }
    }
}
