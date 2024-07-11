package com.example.domain.user.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * ユーザーマスタ（m_user テーブル）のエンティティクラス
 */
@Data
public class MUser {
	private String userId;
	private String password;
	private String userName;
	private Date birthday;
	private Integer age;
	private Integer gender;
	private Integer departmentId;
	private String role;

	/** ユーザーの所属部署情報 */
	private Department department;

	/** ユーザーの給与情報リスト */
	private List<Salary> salaryList;
}
