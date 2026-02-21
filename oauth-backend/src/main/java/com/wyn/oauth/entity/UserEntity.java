package com.wyn.oauth.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  @Id
  @UuidGenerator(style = UuidGenerator.Style.RANDOM)
  @Column(name = "id", nullable = false, updatable = false)
  private String id;

  private String email;

  private String name;
}
