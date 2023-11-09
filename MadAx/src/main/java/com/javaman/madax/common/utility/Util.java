package com.javaman.madax.common.utility;

import java.text.SimpleDateFormat;

public class Util {

	public static int seqNum =1;
	
	 public static String fileRename(String originFileName) {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
         String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

         String str = "_" + String.format("%05d", seqNum++);

         String ext = originFileName.substring(originFileName.lastIndexOf("."));

         return date + str + ext;
      }
}
