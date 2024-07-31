package com.example.business.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Vehicles")
@Getter
@Setter
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

  @OneToOne(mappedBy = "vehicles", fetch = FetchType.LAZY)
  private MaintenanceRecords maintenanceRecords;
}
