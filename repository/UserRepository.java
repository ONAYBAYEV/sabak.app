package kz.narxoz.sabak.app.repository;

import kz.narxoz.sabak.app.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);

    List<User> findByNameStartsWith(String name);



    List <User> findByEmailContainingOrderByNameDesc(String email);
    //2 esep -----------------------------

    List<User> findTop2ByNameStartingWith(String name);
    //3 esep--------------------------

    List<User> findBySurnameContaining(String surname);
    // example surname sort

    @Query(value="select *from users order by surname asc" , nativeQuery = true)
    List<User> findUserByCustomQuery(String surname);
    //example id ----------------------

    @Query(value="select *from users where id> :qid" , nativeQuery = true)
    List<User> findGreaterId(Long qid);
    //4 esep ----------------------

    @Query(value="select *from users order by id asc " , nativeQuery = true)
    List<User> findByIdOrderById(Long id);

    //5 esep-----------------------------

    @Query(value= "select *from users order by id desc limit(2)", nativeQuery = true)
    List<User> findshowlastUsers();
    //6  esep -----------------

    @Query(value= "select *from users order by name desc", nativeQuery = true)
    List<User> sortByName();

    // 7 esep ----------------------
    @Query( value="select *from users where email not like'%@%'", nativeQuery = true)
    List <User> findByEmailNotContaining(String email2);

    // 8
    @Query(value= "select *from users where name=surname",nativeQuery = true)
    List<User>EqualNameSurname(String name1);

    //9
    @Query(value= "select *from users where email like '%narxoz.kz%' or email like '%gmail.com%' or email like '%yandex.ru%' ",
            nativeQuery = true)
    List<User> emailLike();
    //10
    @Query(value="select distinct on (name) * from users ", nativeQuery = true)
    List<User> findDistinctByName();
}
