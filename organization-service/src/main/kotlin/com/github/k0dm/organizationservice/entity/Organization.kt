package com.github.k0dm.organizationservice.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
@Entity
@Table(name = "organizations")
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(nullable = false)
    var organizationName: String,
    var organizationDescription:String,
    @Column(nullable = false, unique = true)
    var organizationCode: String,
    @CreationTimestamp
    var createdDate: LocalDateTime?
)