package system.systemuser.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import system.systemuser.entity.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, String> {

}
