package com.example.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全てのコントローラーに対するグローバルな例外処理を定義するクラス
 */
@ControllerAdvice
public class GlobalControllerAdvice {

	/**
	 * データベース関連の例外処理
	 * @param e 発生したDataAccessException
	 * @param model ビューに渡すモデル
	 * @return エラーページのビュー名
	 */
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		// エラータイプを空文字としてセット（セキュリティ上の理由でエラーの詳細を隠蔽）
		model.addAttribute("error", "");

		// ユーザーフレンドリーなエラーメッセージをModelに登録
		model.addAttribute("message", "DataAccessExceptionが発生しました");

		// HTTPのエラーコード（500 Internal Server Error）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		// エラーページのビュー名を返す
		return "error";
	}

	/**
	 * その他の例外処理
	 * @param e 発生した例外
	 * @param model ビューに渡すモデル
	 * @return エラーページのビュー名
	 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		// エラータイプを空文字としてセット（セキュリティ上の理由でエラーの詳細を隠蔽）
		model.addAttribute("error", "");

		// ユーザーフレンドリーなエラーメッセージをModelに登録
		model.addAttribute("message", "Exceptionが発生しました");

		// HTTPのエラーコード（500 Internal Server Error）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		// エラーページのビュー名を返す
		return "error";
	}
}
