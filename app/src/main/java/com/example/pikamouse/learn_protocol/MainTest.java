package com.example.pikamouse.learn_protocol;

/**
 * create by liting 2018/11/10
 */
public class MainTest {

    public static void main(String[]args){

    }









    private static byte[] int2ByteArrays(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }
}
