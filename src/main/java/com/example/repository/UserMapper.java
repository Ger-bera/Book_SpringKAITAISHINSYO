package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.user.model.MUser;

/**
 * ユーザー関連のデータベース操作を定義するマッパーインターフェース
 * MyBatisを使用してSQLマッピングを行います
 */
@Mapper
public interface UserMapper {

	/**
	 * 単一のユーザーを登録する
	 * @param user 登録するユーザー情報
	 * @return 影響を受けた行数（通常は1）
	 */
	public int insertOne(MUser user);

	/**
	 * ユーザー情報を検索条件に基づいて取得する
	 * @param user 検索条件を含むMUserオブジェクト
	 * @return 条件に合致するユーザー情報のリスト
	 */
	public List<MUser> findMany(MUser user);

	/**
	 * 指定されたユーザーIDに対応するユーザー情報を1件取得する
	 * @param userId 検索対象のユーザーID
	 * @return 該当するユーザー情報。見つからない場合はnull
	 */
	public MUser findOne(String userId);

	/**
	 * ユーザー情報を1件更新する
	 * @param userId 更新対象のユーザーID
	 * @param password 新しいパスワード
	 * @param userName 新しいユーザー名
	 */
	public void updateOne(@Param("userId") String userId,
			@Param("password") String password,
			@Param("userName") String userName);

	/**
	 * ユーザーを1件削除する
	 * @param userId 削除対象のユーザーID
	 * @return 影響を受けた行数（通常は1）
	 */
	public int deleteOne(@Param("userId") String userId);

	/** ログインユーザー取得 */
	public MUser findLoginUser(String userId);
}
