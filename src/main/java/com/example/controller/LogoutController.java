package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * ログアウト処理を担当するコントローラークラス
 */
@Controller
@Slf4j // Lombokのアノテーション: SLF4Jのロガーを自動生成
public class LogoutController {

	/**
	 * ログアウト処理を行い、ログイン画面にリダイレクトするハンドラーメソッド
	 *
	 * @return ログイン画面へのリダイレクトパス
	 */
	@PostMapping("/logout")
	public String postLogout() {
		log.info("ログアウト"); // ログアウト操作のログを出力
		// TODO: セッション無効化などの実際のログアウト処理を実装する
		return "redirect:/login";
	}

	// 注意: 現在の実装では、実際のログアウト処理（セッション無効化など）が行われていません。
	// セキュリティ上の理由から、適切なログアウトメカニズムを実装する必要があります。
}
