package com.project.wifi.bookmark;

import com.project.wifi.bookmark.dto.BookMarkDto;
import com.project.wifi.bookmark.dto.BookMarkGroupDto;
import com.project.wifi.bookmark.dto.BookMarkGroupResponseDto;
import com.project.wifi.bookmark.dto.BookMarkResponseDto;

import java.io.IOException;
import java.util.List;

public class BookMarkServiceImpl {
    public void insertBookMarkGroup(String bookMarkName, String bookMarkOrder) throws IOException {

        BookMarkRepository bookMarkRepository = new BookMarkRepository();
        BookMarkGroupDto bookMarkGroupDto = new BookMarkGroupDto();
        bookMarkGroupDto.setBookMarkName(bookMarkName);
        bookMarkGroupDto.setBookMarkOrder(bookMarkOrder);

        bookMarkRepository.insertBookMarkGroup(bookMarkGroupDto);

    }

    public void insertBookMark(String bookMarkSeq, String wifiManagerNo) throws IOException {

        BookMarkRepository bookMarkRepository = new BookMarkRepository();
        BookMarkDto bookMarkDto = new BookMarkDto();
        bookMarkDto.setBookMarkSeq(bookMarkSeq);
        bookMarkDto.setWifiManageNo(wifiManagerNo);

        bookMarkRepository.insertBookMark(bookMarkDto);

    }

    public List<BookMarkGroupResponseDto> selectBookMarkGroupList() throws IOException {
        BookMarkRepository bookMarkRepository = new BookMarkRepository();
        return bookMarkRepository.selectBookMarkGroupList();
    }

    public BookMarkGroupResponseDto selectBookMarkGroup(String seq) throws IOException {
        BookMarkRepository bookMarkRepository = new BookMarkRepository();
        return bookMarkRepository.selectBookMarkGroup(seq);
    }

    public void updateBookMarkGroup(BookMarkGroupDto bookMarkGroupDto) throws IOException {
        BookMarkRepository bookMarkRepository = new BookMarkRepository();
        bookMarkRepository.updateBookMarkGroup(bookMarkGroupDto);
    }

    public void deleteBookMarkGroup(BookMarkGroupDto bookMarkGroupDto) {
        BookMarkRepository bookMarkRepository = new BookMarkRepository();
        bookMarkRepository.deleteBookMarkGroup(bookMarkGroupDto);
    }

    public List<BookMarkResponseDto> selectBookMarkList() {
        BookMarkRepository bookMarkRepository = new BookMarkRepository();
        return bookMarkRepository.selectBookMarkList();
    }

    public BookMarkResponseDto selectBookMark(String seq) {
        BookMarkRepository bookMarkRepository = new BookMarkRepository();
        return bookMarkRepository.selectBookMark(seq);
    }

    public void deleteBookMark(BookMarkDto bookMarkDto) {
        BookMarkRepository bookMarkRepository = new BookMarkRepository();
        bookMarkRepository.deleteBookMark(bookMarkDto);
    }
}
