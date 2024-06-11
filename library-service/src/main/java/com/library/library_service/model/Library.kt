package com.library.library_service.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator

@Entity
data class Library @JvmOverloads constructor(
    @Id
    @Column(name = "library_id")
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    val id: String? = "",

    @ElementCollection
    val userBook: List<String> = ArrayList(),


)
