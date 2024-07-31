package com.example.business.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String role;
  private String idMaintenance;
  private String idVehicles;
  @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Vehicles> vehicles;
}
