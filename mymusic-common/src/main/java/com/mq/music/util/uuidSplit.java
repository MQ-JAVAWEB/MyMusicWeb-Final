package com.mq.music.util;

public class uuidSplit {

        public static String createUUID(int len) {

            String uuid = java.util.UUID.randomUUID().toString()
                    .replaceAll("-", "");
            return uuid.substring(0, len);
        }
//        public static void main(String[] args) {
////            int len=6;
////            String uuid = java.util.UUID.randomUUID().toString()
////                    .replaceAll("-", "");
////            System.out.println(uuid.substring(0, len));
////
////            System.out.println(createUUID(3));
////
////        }

}
