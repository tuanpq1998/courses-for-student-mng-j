CREATE DATABASE course_system_management;

USE course_system_management;

CREATE TABLE users_courses(
	`user_id` INT NOT NULL,
    `course_id` INT NOT NULL,
    primary key(`user_id`, `course_id`),
    CONSTRAINT `fk_userid_user` FOREIGN KEY (`user_id`) 
	REFERENCES `user` (`id`),
    
    CONSTRAINT `fk_courseid_course` FOREIGN KEY (`course_id`) 
	REFERENCES `course` (`id`)
) ENGINE=InnoDB;