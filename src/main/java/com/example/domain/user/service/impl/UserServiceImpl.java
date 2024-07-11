package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

/**
 * UserServiceの実装クラス
 * ユーザー関連のビジネスロジックを実装する
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	/**
	 * ユーザー登録を行う
	 * @param user 登録するユーザー情報
	 */
	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1);// 部署ID設定（固定値）
		user.setRole("ROLE_GENERAL");// ロール設定（一般ユーザー）

		// パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));

		mapper.insertOne(user);
	}

	/**
	 * ユーザー情報を検索条件に基づいて取得する
	 * @param user 検索条件を含むMUserオブジェクト
	 * @return 条件に合致するユーザー情報のリスト
	 */
	@Override
	public List<MUser> getUsers(MUser user) {
		return mapper.findMany(user);
	}

	/**
	 * 指定されたユーザーIDに対応するユーザー情報を1件取得する
	 * @param userId 検索対象のユーザーID
	 * @return 該当するユーザー情報。見つからない場合はnull
	 */
	@Override
	public MUser getUserOne(String userId) {
		return mapper.findOne(userId);
	}

	/**
	 * ユーザー情報を1件更新する
	 * @param userId 更新対象のユーザーID
	 * @param password 新しいパスワード
	 * @param userName 新しいユーザー名
	 */
	@Transactional
	@Override
	public void updateUserOne(String userId, String password, String userName) {

		// パスワード暗号化
		String encryptPassword = encoder.encode(password);

		mapper.updateOne(userId, encryptPassword, userName);

		// 例外を発生させる
		//int i = 1/0;

	}

	/**
	 * ユーザーを1件削除する
	 * @param userId 削除対象のユーザーID
	 */
	@Override
	public void deleteUserOne(String userId) {
		int count = mapper.deleteOne(userId);
	}

	/** ログインユーザー情報取得 */
	@Override
	public MUser getLoginUser(String userId) {
		return mapper.findLoginUser(userId);
	}
}
