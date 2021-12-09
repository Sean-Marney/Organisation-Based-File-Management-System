insert into organisations values(1, 'Cardiff University');
insert into organisations values(2, 'Swansea University');
insert into organisations values(3, 'Welsh Government');
insert into organisations values(4, 'Science and Technology Facilities Council');

INSERT INTO `graphium`.`users` (`user_id`, `username`, `first_name`, `last_name`, `pass`, `enabled`, `role`) VALUES ('1', 'ose', 'ose', 'inn', '$2a$12$D8Dcp2FCJUhG52E99a80Gevpm0PFE/78oPEGBwQLXusR4EFFKLAhG', '1', 'USER');
INSERT INTO `graphium`.`users` (`user_id`, `username`, `first_name`, `last_name`, `pass`, `enabled`, `role`) VALUES ('2', 'admin', 'admin', 'admin', '$2a$12$D8Dcp2FCJUhG52E99a80Gevpm0PFE/78oPEGBwQLXusR4EFFKLAhG', '1', 'ORGANISATION');
