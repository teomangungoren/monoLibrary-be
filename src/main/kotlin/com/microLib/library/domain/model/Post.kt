package com.microLib.library.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@Entity
@Table(name = "post")
@DynamicUpdate
data class Post(
    var postTitle:String,
    var description:String,
    val ownerUsername:String,
    var likes:Int = 0,
    @CreatedDate var createdAt: Instant? = null,
    @LastModifiedDate var updatedAt: Instant? = null
):BaseEntity()
