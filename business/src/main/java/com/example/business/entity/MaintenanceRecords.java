package com.example.business.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "MaintenanceRecords")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecords {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date serviceDate;

  private String description;
  private float associatedCost;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "vehicles_id", referencedColumnName = "id")
  private Vehicles vehicles;
}
