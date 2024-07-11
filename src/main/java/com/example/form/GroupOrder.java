package com.example.form;

import javax.validation.GroupSequence;

// バリデーショングループの実行順序を定義するインターフェース
@GroupSequence({ ValidGroup1.class, ValidGroup2.class})
public interface GroupOrder {
	// このインターフェースは空で、アノテーションのみを持ちます

	// 注意: このインターフェースはバリデーショングループの実行順序を制御します
	// ValidGroup1のバリデーションが成功した後にのみ、ValidGroup2のバリデーションが実行されます
}
