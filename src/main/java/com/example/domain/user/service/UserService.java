package com.example.domain.user.service;

import java.util.List;

import com.example.domain.user.model.MUser;

/**
 * ユーザー関連のビジネスロジックを定義するサービスインターフェース
 * このインターフェースはユーザー管理の主要な操作を定義します
 */
public interface UserService {

	/**
	 * ユーザー登録を行う
	 * @param user 登録するユーザー情報
	 */
	public void signup(MUser user);

	/**
	 * ユーザー情報を検索条件に基づいて取得する
	 * @param user 検索条件を含むMUserオブジェクト
	 * @return 条件に合致するユーザー情報のリスト
	 */
	public List<MUser> getUsers(MUser user);

	/**
	 * 指定されたユーザーIDに対応するユーザー情報を1件取得する
	 * @param userId 検索対象のユーザーID
	 * @return 該当するユーザー情報。見つからない場合はnull
	 */
	public MUser getUserOne(String userId);

	/**
	 * ユーザー情報を1件更新する
	 * @param userId 更新対象のユーザーID
	 * @param password 新しいパスワード
	 * @param userName 新しいユーザー名
	 */
	public void updateUserOne(String userId, String password, String userName);

	/**
	 * ユーザーを1件削除する
	 * @param userId 削除対象のユーザーID
	 */
	public void deleteUserOne(String userId);

	/** ログインユーザー情報取得 */
	public MUser getLoginUser(String userId);
}
