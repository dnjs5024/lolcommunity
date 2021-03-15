package com.example.demo.community.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PwdSolt {
	private final static String SOLT="thrma";
	public static String transPwd(String pwd) {
		return DigestUtils.sha256Hex(pwd+SOLT);
	}

}
