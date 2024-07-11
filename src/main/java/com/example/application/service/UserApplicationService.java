package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * ユーザー関連のアプリケーションサービスクラス
 * ユーザー登録や管理に関連する業務ロジックを提供する
 */
@Service
public class UserApplicationService {

	// MessageSourceをインジェクトして国際化メッセージを取得
	@Autowired
	private MessageSource messageSource;

	/**
	 * 性別の選択肢を生成する
	 * @param locale 使用するロケール
	 * @return 性別のマップ（キー：表示名、値：ID）
	 */
	public Map<String, Integer> getGenderMap(Locale locale) {
		// LinkedHashMapを使用することで、挿入順序が保持される
		Map<String, Integer> genderMap = new LinkedHashMap<>();

		// メッセージソースから性別の表示名を取得（指定されたロケールを使用）
		String male = messageSource.getMessage("male", null, locale);
		String female = messageSource.getMessage("female", null, locale);

		// 性別の選択肢をマップに追加
		genderMap.put(male, 1);
		genderMap.put(female, 2);

		// 生成したマップを返す
		return genderMap;
	}
}
