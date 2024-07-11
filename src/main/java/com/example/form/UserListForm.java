package com.example.form;

import lombok.Data;

/**
 * ユーザー一覧画面で使用されるフォームクラス
 * 検索条件やフィルタリングに使用されるデータを保持します
 */
@Data
public class UserListForm {
	/** ユーザーID（検索条件） */
	private String userId;

	/** ユーザー名（検索条件） */
	private String userName;
}
