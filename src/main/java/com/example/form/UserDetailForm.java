package com.example.form;

import java.util.Date;
import java.util.List;

import com.example.domain.user.model.Department;
import com.example.domain.user.model.Salary;

import lombok.Data;

/**
 * ユーザー詳細情報を保持するフォームクラス
 * 画面とのデータのやり取りに使用される
 */
@Data
public class UserDetailForm {
	private String userId;
	private String password;
	private String userName;
	private Date birthday;
	private Integer age;
	private Integer gender;

	/** ユーザーの所属部署情報 */
	private Department department;

	/** ユーザーの給与情報リスト */
	private List<Salary> salaryList;
}
