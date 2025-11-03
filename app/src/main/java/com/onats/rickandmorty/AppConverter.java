package com.onats.rickandmorty;

public class AppConverter {

    public static byte[] generateKeyExchangeRequest(String terminalId) {
        String sMessage = "H0.ATMEFT\u001C00000027\u001C88\u001C3";

        byte[] header = new byte[2];

        ToB2(sMessage.length(), header);

        byte[] bMessage = new byte[sMessage.length() + 2];

        System.arraycopy(header, 0, bMessage, 0, header.length);
        System.arraycopy(sMessage.getBytes(), 0, bMessage, header.length, sMessage.length());

        return bMessage;
    }

    public static void ToB2(int length, byte[] data) {
        data[0] = (byte) ((length & 0x0000FF00) >> 8);
        data[1] = (byte) (length & 0x000000FF);
    }
}
