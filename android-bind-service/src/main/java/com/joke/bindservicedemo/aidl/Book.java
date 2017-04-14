package com.joke.bindservicedemo.aidl;

import android.os.Parcel;
import android.os.Parcelable;


public class Book implements Parcelable {
    public int bookId;
    public String bookName;

    public Book() {
    }

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }


    /**
     * 将相应的输入流转换为Book对象
     * @param in 输入流
     */
    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }


    /**
     * 将相应的对象转换为输出对象
     * @param dest The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     * flags的作用主要是为了为了给在使用{@link #writeToParcel}增加一个flag标记：
     *       它的作用是，当一个object被输入的时候，将具有一个返回值，
     *       这个返回值的作用是，这个函数被调用的结果的一个声明。
     *       默认情况下只有 0 或者{@link #PARCELABLE_WRITE_RETURN_VALUE}
     */
    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }


    /**
     * 默认的Parcel实现
     */
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    /**
     *   默认情况下返回0，
     *   我们在Parcelable中相应的方法描述可以看到，如果在执行
     *   {@link #writeToParcel(Parcel, int)}相应的Object中包含文件描述符，
     *   那么应该，返回的是{@link #CONTENTS_FILE_DESCRIPTOR}
     *   @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override public int describeContents() {
        return 0;
    }


    @Override public String toString() {
        return String.format("[bookId:%s, bookName:%s]", bookId, bookName);
    }

}
