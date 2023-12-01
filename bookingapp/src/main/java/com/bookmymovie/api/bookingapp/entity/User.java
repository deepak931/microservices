package com.bookmymovie.api.bookingapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native")
  private Long userId;

  private String name;

  private String email;

  private String phoneNo;
  @OneToMany
  private Set<Booking> bookings;
}
