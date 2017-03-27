package example.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.bean.UserBean;

public interface UserRespository extends JpaRepository<UserBean, Long> {
}
