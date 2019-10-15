package com.gujun.springbootrabbitmq01.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias(value = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 8936911712741389907L;

    private String bookName;

    private double price;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Book() {
    }

    public Book(String bookName, double price) {
        this.bookName = bookName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", price=" + price +
                '}';
    }

}
