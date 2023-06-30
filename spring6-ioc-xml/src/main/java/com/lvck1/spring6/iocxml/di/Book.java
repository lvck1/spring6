package com.lvck1.spring6.iocxml.di;

public class Book {

    private String bname;
    private String author;

    private String others;

    public Book() {
        System.out.println("无参构造执行...");
    }

    public Book(String bname, String author) {
        System.out.println("有参构造执行...");
        this.bname = bname;
        this.author = author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", others='" + others + '\'' +
                '}';
    }
}
