package com.javaman.madax.email.model.service;

import java.util.Map;

public interface EmailService {

	/** 이메일 발송
	 * @param string
	 * @param email
	 * @return
	 */
	int sendEmail(String string, String email);

	/** 인증 번호
	 * @param paramMap
	 * @return
	 */
	int checkAuthKey(Map<String, Object> paramMap);

}
