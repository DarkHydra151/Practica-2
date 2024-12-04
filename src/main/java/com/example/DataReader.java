package com.example;

import java.util.List;

public interface DataReader<T> {
    List<T> read(String source);
}
