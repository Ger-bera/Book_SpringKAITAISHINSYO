package com.example.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * ユーザー登録フォームのデータを保持するクラス
 */
@Data // Lombokアノテーション: getter, setter, toString, equals, hashCodeメソッドを自動生成
public class SignupForm {

	// ユーザーID（メールアドレス）
	@NotBlank(groups = ValidGroup1.class) // 必須入力チェック（第1段階）
	@Email(groups = ValidGroup2.class) // メールアドレス形式チェック（第2段階）
	private String userId;

	// パスワード
	@NotBlank(groups = ValidGroup1.class) // 必須入力チェック（第1段階）
	@Length(min = 4, max = 100, groups = ValidGroup2.class) // 長さチェック（第2段階）
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class) // 英数字のみ許可（第2段階）
	private String password;

	// ユーザー名
	@NotBlank(groups = ValidGroup1.class) // 必須入力チェック（第1段階）
	private String userName;

	// 誕生日
	@DateTimeFormat(pattern = "yyyy/MM/dd") // 日付フォーマット指定
	@NotNull(groups = ValidGroup1.class) // 必須入力チェック（第1段階）
	private Date birthday;

	// 年齢
	@Min(value = 20, groups = ValidGroup2.class) // 最小値チェック（第2段階）
	@Max(value = 100, groups = ValidGroup2.class) // 最大値チェック（第2段階）
	private Integer age;

	// 性別（通常は1:男性, 2:女性など）
	@NotNull(groups = ValidGroup1.class) // 必須入力チェック（第1段階）
	private Integer gender;

	// 注意: バリデーションは ValidGroup1 -> ValidGroup2 の順で実行されます
	// これにより、必須チェック（ValidGroup1）が先に行われ、その後詳細なチェック（ValidGroup2）が行われます
}
