insert into address (id, address, zip_code) values(1, '北京市海淀区', 10010);
insert into address (id, address, zip_code) values(2, '北京市昌平区', 10011);
insert into address (id, address, zip_code) values(3, '北京市朝阳区', 10012);
insert into student (id, name, sex, age, address_id) values(1, '小明', 1, 18, 1);
insert into student (id, name, sex, age, address_id) values(2, '小红', 2, 16, 2);
insert into student (id, name, sex, age, address_id) values(3, '小强', 1, 15, 3);
insert into grade (id, name) values(1, '教授');
insert into grade (id, name) values(2, '副教授');
insert into grade (id, name) values(3, '讲师');
insert into grade (id, name) values(4, '助理');
insert into teacher (id, name, grade_id) values(1, '王老师', 1);
insert into teacher (id, name, grade_id) values(2, '张老师', 1);
insert into teacher (id, name, grade_id) values(3, '李老师', 2);
insert into teacher (id, name, grade_id) values(4, '陈老师', 3);
insert into teacher (id, name, grade_id) values(5, '赵老师', 4);
insert into student_teacher (student_id, teacher_id) values(1, 1);
insert into student_teacher (student_id, teacher_id) values(1, 2);
insert into student_teacher (student_id, teacher_id) values(2, 3);
insert into student_teacher (student_id, teacher_id) values(2, 4);
insert into student_teacher (student_id, teacher_id) values(3, 5);
insert into student_teacher (student_id, teacher_id) values(3, 1);