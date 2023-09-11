package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* @Component의 세분화 어노테이션의 한 종류로 Service 계층으로 사용 한다. */
@Service("bookServiceField")
public class BookService {

    /* BookDAO 타입의 빈 객체를 이 프로퍼티에 자동으로 주입해준다. */
    @Autowired
    private BookDAO bookDAO;

    /* 도서 목록 전체 조회 */
    public List<BookDTO> selectAllBooks() {

        return bookDAO.selectBookList();
    }

    public BookDTO searchBookBySequence(int sequence){
        return  bookDAO.selectOneBook(sequence);
    }
}
