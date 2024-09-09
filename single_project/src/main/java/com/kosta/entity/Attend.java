//package com.kosta.entity;
//
//import java.time.LocalDateTime;
//
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import com.kosta.domain.UserRole;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//@RequiredArgsConstructor
//@Data
//public class Attend {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(updatable = false)
//	private Long id;
//	
//	@JoinColumn(name = "student_id")
//	@ManyToOne
//	private User student;
//	
//	@JoinColumn(name = "course_id")
//	@ManyToOne
//	private Course course;
//
//	@Column
//	private int score;
//	
//	@CreatedDate
//	@Column(name = "created_at")
//	private LocalDateTime createdAt;
//
//	@LastModifiedDate
//	@Column(name = "updated_at")
//	private LocalDateTime updatedAt;
//}
