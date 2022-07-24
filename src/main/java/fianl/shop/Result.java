package fianl.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int count ;  //{}객체로 싸서 반환 하는 장점임
    private T data;
    private String message;
    public Result(String s){
        message= s;
    }
}