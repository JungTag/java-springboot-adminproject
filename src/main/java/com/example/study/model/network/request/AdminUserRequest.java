package com.example.study.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserRequest {
   private Long id;
   private String account;
   private String password;
   private String status;
   private String role;
   private LocalDateTime lastLoginAt;
   private LocalDateTime passwordUpdatedAt;
   private int loginFailCount; // null로 저장됨, DB에서 0으로 변환하는 과정에서 에러!!
   private LocalDateTime registeredAt;
   private LocalDateTime unregisteredAt;
}

