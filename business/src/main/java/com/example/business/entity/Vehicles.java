package com.example.business.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicles {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String make;

  private String model;

  private String year;

  private String type;

  @ManyToOne
  @JoinColumn(name = "ID_USER")
  private Users users;

  @OneToOne(mappedBy = "vehicles")
  private MaintenanceRecords maintenanceRecords;
}
