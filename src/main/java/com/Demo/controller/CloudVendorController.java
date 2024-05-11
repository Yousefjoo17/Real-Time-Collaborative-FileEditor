package com.Demo.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Demo.exception.CloudVendorNotFoundException;
import com.Demo.model.CloudVendor;
import com.Demo.service.CloudVendorService;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController
{
    CloudVendorService cloudVendorService; 

    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    // Read Specific Cloud Vendor Details from DB
    @GetMapping("/{vendorId}")
    public CloudVendor getCloudVendorDetails(@PathVariable("vendorId") String vendorId)
    {
       return cloudVendorService.getCloudVendor(vendorId);
    }

    // Read All Cloud Vendor Details from DB
    @GetMapping("/")
    public List<CloudVendor> getAllCloudVendorDetails()
    {
        if(cloudVendorService.getAllCloudVendors().isEmpty())
        throw new CloudVendorNotFoundException("There is new infor");

        return cloudVendorService.getAllCloudVendors();
    }

    @PostMapping("/")
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor)
    {
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor Created Successfully";
    }

    @PutMapping("/")
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor)
    {
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "Cloud Vendor Updated Successfully";
    }

    @DeleteMapping("/{vendorId}")
    public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId)
    {
        if (cloudVendorService.getByVendorName(vendorId).isEmpty())
        throw new CloudVendorNotFoundException("the vendor is already not found!");
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Cloud Vendor Deleted Successfully";
    }
}
