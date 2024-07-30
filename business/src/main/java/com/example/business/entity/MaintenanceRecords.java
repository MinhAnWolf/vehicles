package com.example.business.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "MaintenanceRecords")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecords {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date serviceDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "vehicles_id", referencedColumnName = "id")
  private Vehicles vehicles;
}
