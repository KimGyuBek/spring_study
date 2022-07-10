package com.example.testproject.common;

public class Constant {
    public enum ExceptionClass {

        PRODUCT("Product"), ORDER("Order"), PROVIDER("Proider");

        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception. ";
        }
    }
}
