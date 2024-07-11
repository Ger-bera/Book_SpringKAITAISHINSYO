package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ログイン関連の処理を担当するコントローラークラス
 * ログイン画面の表示や認証処理を管理する
 */
@Controller
public class LoginController {

	/**
	 * ログイン画面を表示するハンドラーメソッド
	 *
	 * @return ログイン画面のビュー名
	 */
	@GetMapping("/login")
	public String getLogin() {
		// "login/login"は、src/main/resources/templates/login/login.html を指す
		return "login/login";
	}

	/**
	 * ログイン処理を行うハンドラーメソッド
	 *
	 * @return ユーザー一覧画面へのリダイレクトパス
	 */
	@PostMapping("/login")
	public String postLogin() {
		// TODO: 実際の認証処理を実装する
		return "redirect:/user/list";
	}

	// 注意: 現在の実装では、実際の認証処理が行われていません。
	// セキュリティ上の理由から、適切な認証メカニズムを実装する必要があります。
}
