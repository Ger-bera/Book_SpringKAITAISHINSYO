package com.example.domain.user.model;

import lombok.Data;

/**
 * 給与情報を表すモデルクラス
 */
@Data
public class Salary {
	/** ユーザーID */
	private String userId;

	/** 年月 (例: "2023-07") */
	private String yearMonth;

	/** 給与額 */
	private Integer salary;
}
