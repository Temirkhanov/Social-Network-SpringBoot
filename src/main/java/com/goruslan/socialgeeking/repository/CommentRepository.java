package com.goruslan.socialgeeking.repository;

import com.goruslan.socialgeeking.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.
 *
 * Their main functions are:
 *      - CrudRepository mainly provides CRUD functions.
 *      - PagingAndSortingRepository provides methods to do pagination and sorting records.
 *      - JpaRepository provides some JPA-related methods such as flushing the persistence
 *        context and deleting records in a batch.
 *
 * JpaRepository will have all the functions of CrudRepository and PagingAndSortingRepository.
 * So if you don't need the repository to have the functions provided by
 * JpaRepository and PagingAndSortingRepository use CrudRepository.
 */


public interface CommentRepository extends JpaRepository<Comment, Long> {

}
