package com.god.store_manager.controller.admin;

import com.god.store_manager.model.location.District;
import com.god.store_manager.model.location.Province;
import com.god.store_manager.model.location.Ward;
import com.god.store_manager.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminLocationController {
    private final LocationService locationService;

    @PostMapping("/location/province/add")
    public ResponseEntity addProvince(@RequestBody Province province){
        if(locationService.addProvince(province)){
            return ResponseEntity.ok(province);
        }
        else {
            return ResponseEntity.status(500).body("Something wrent wrong, please try again later");
        }
    }
    @PostMapping("/location/district/add")
    public ResponseEntity addDistrict(@RequestBody District district){
        if(locationService.addDistrict(district)){
            return ResponseEntity.ok(district);
        }
        else {
            return ResponseEntity.status(500).body("Something wrent wrong, please try again later");
        }
    }
    @PostMapping("/location/ward/add")
    public ResponseEntity addWard(@RequestBody Ward ward){
        if(locationService.addWard(ward)){
            return ResponseEntity.ok(ward);
        }
        else {
            return ResponseEntity.status(500).body("Something wrent wrong, please try again later");
        }
    }
    @PostMapping("/location/province/update")
    public ResponseEntity updateProvince(@RequestBody Province province){
        if(locationService.updateProvince(province)){
            return ResponseEntity.ok(province);
        }
        else {
            return ResponseEntity.status(500).body("Something wrent wrong, please try again later");
        }
    }
    @PostMapping("/location/district/update")
    public ResponseEntity updateDistrict(@RequestBody District district){
        if(locationService.updateDistrict(district)){
            return ResponseEntity.ok(district);
        }
        else {
            return ResponseEntity.status(500).body("Something wrent wrong, please try again later");
        }
    }
    @PostMapping("/location/ward/update")
    public ResponseEntity updateWard(@RequestBody Ward ward){
        if(locationService.updateWard(ward)){
            return ResponseEntity.ok(ward);
        }
        else {
            return ResponseEntity.status(500).body("Something wrent wrong, please try again later");
        }
    }
    @PostMapping("/location/province/delete")
    public ResponseEntity deleteProvince(@RequestBody long provinceId){
        if(locationService.deleteProvince(provinceId)){
            return ResponseEntity.ok(provinceId);
        }
        else {
            return ResponseEntity.status(500).body("Something wrent wrong, please try again later");
        }
    }
    @PostMapping("/location/district/delete")
    public ResponseEntity deleteDistrict(@RequestBody long districtId){
        if(locationService.deleteDistrict(districtId)){
            return ResponseEntity.ok(districtId);
        }
        else {
            return ResponseEntity.status(500).body("Something wrent wrong, please try again later");
        }
    }
    @PostMapping("/location/ward/delete")
    public ResponseEntity deleteWard(@RequestBody long wardId){
        if(locationService.deleteWard(wardId)){
            return ResponseEntity.ok(wardId);
        }
        else {
            return ResponseEntity.status(500).body("Something wrent wrong, please try again later");
        }
    }
}
