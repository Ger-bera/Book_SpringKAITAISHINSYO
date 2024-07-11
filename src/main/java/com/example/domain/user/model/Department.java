package com.example.domain.user.model;

import lombok.Data;

/**
 * 部署情報を表すモデルクラス
 */
@Data // Lombokアノテーション: getter, setter, toString, equals, hashCodeメソッドを自動生成
public class Department {
	private Integer departmentId; // 部署ID
	private String departmentName; // 部署名
}
