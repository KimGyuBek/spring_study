package com.example.testproject.data.repository;

import com.example.testproject.data.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

//    void find

//     query 메서드를 명명하고 구현체를 따로 만들지 않아고 jpa에서 알아서 만들어준다.
    ShortUrl findByUrl(String url);

    ShortUrl findByOrgUrl(String orginalUrl);

}
