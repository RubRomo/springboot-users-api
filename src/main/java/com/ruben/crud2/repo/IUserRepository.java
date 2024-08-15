    package com.ruben.crud2.repo;

    import com.ruben.crud2.model.User;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    public interface IUserRepository extends JpaRepository<User, Integer> {

        User findByUsername(String username);
    }
