package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUserRequest;
import com.example.study.model.network.response.AdminUserResponse;
import com.example.study.service.AdminUserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController implements CrudInterface<AdminUserRequest, AdminUserResponse> {
    @Autowired
    AdminUserApiLogicService adminUserApiLogicService;

    @Override
    @PostMapping("")
    public Header<AdminUserResponse> create(@RequestBody Header<AdminUserRequest> request) {
        return adminUserApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<AdminUserResponse> read(@PathVariable Long id) {
        return adminUserApiLogicService.read(id);
    }

    @Override
    @PutMapping
    public Header<AdminUserResponse> update(@RequestBody Header<AdminUserRequest> request) {
        return adminUserApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return adminUserApiLogicService.delete(id);
    }
}
