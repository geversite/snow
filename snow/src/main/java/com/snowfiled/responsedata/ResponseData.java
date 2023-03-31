package com.snowfiled.responsedata;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseData<T> {
    private int status;
    private String message;
    private T data;

    public ResponseData(Builder<T> builder){
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static <T> Builder<T> builder(){
        return new Builder<T>();
    }

    public static class Builder<T>{
        private int status;
        private String message;
        private T data;

        public Builder<T> status(int status){
            this.status = status;
            return this;
        }

        public Builder<T> successs(){
            this.status = 200;
            return this;
        }

        public Builder<T> message(String message){
            this.message = message;
            return this;
        }

        public Builder<T> data(T data){
            this.data = data;
            return this;
        }

        public ResponseData<T> build(){
            return new ResponseData<>(this);
        }
    }




}



