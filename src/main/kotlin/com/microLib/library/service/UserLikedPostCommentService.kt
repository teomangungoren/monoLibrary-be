package com.microLib.library.service

import com.microLib.library.domain.model.UserLikedComment
import com.microLib.library.domain.request.CreateUserLikePostCommentRequest
import com.microLib.library.repository.UserLikedCommentRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class UserLikedPostCommentService (private val userLikedCommentRepository: UserLikedCommentRepository,
                                   private val postCommentService: PostCommentService) {
    @Transactional
    fun create(request: CreateUserLikePostCommentRequest){
        val username= SecurityContextHolder.getContext().authentication.name
        with(request){
            val userLikedComment= UserLikedComment(postCommentId,username, Instant.now(), Instant.now())
            userLikedCommentRepository.save(userLikedComment)
        }
        postCommentService.findById(request.postCommentId)?.let {
            postCommentService.increaseLikeCount(it.id!!)
        }
    }

    fun delete(commentId:String){
        val username= SecurityContextHolder.getContext().authentication.name
        userLikedCommentRepository.findUserLikedCommentByIdAndUsername(commentId,username)?.let {
            userLikedCommentRepository.delete(it)
            postCommentService.decreaseLikeCount(commentId)
        }
    }
}