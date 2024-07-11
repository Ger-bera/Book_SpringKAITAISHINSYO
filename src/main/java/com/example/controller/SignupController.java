package com.example.controller;

import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

/**
 * ユーザー登録に関連する処理を担当するコントローラークラス
 */
@Controller
@RequestMapping("/user")
@Slf4j // Lombokのアノテーション: SLF4Jのロガーを自動生成
public class SignupController {

	/**
	 * ユーザー関連のアプリケーションサービス
	 * 性別などのユーザー情報を取得するために使用
	 */
	@Autowired
	private UserApplicationService userApplicationService;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * ユーザー登録画面を表示するハンドラーメソッド
	 *
	 * @param model ビューに渡すデータを格納するオブジェクト
	 * @param locale 現在のロケール情報
	 * @param form SignupFormオブジェクト（@ModelAttributeにより自動でモデルに追加される）
	 * @return ユーザー登録画面のビュー名
	 */
	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {
		// ユーザーアプリケーションサービスから性別のマップを取得
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		// 取得した性別マップをモデルに追加
		model.addAttribute("genderMap", genderMap);
		// ユーザー登録画面に遷移
		return "user/signup";
	}

	/**
	 * ユーザー登録処理を行うハンドラーメソッド
	 *
	 * @param model モデルオブジェクト
	 * @param locale 現在のロケール情報
	 * @param form 送信されたフォームデータ（@ModelAttributeによりバインドされる）
	 * @param bindingResult バリデーション結果を保持するオブジェクト
	 * @return リダイレクト先のパスまたはエラー時の遷移先
	 */
	@PostMapping("/signup")
	public String postSignup(Model model, Locale locale,
			@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
			BindingResult bindingResult) {

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			// エラーがある場合：ユーザー登録画面に戻る
			return getSignup(model, locale, form);
		}

		// フォームの内容をログに出力（デバッグ用）
		log.info(form.toString());

		// formをMUserクラスに変換
		// SignupFormの全フィールドの値が対応するMUserのフィールドにコピーされます
		MUser user = modelMapper.map(form, MUser.class);

		// ユーザー登録
		userService.signup(user);

		// ログイン画面にリダイレクト
		return "redirect:/login";
	}

	/** データベース関連の例外処理 */
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {

		// 空文字をセット（エラータイプを表示しない）
		model.addAttribute("error", "");

		// メッセージをModelに登録
		model.addAttribute("message", "SignupControllerで例外が発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

	/** その他の例外処理 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {

		// 空文字をセット（エラータイプを表示しない）
		model.addAttribute("error", "");

		// メッセージをModelに登録
		model.addAttribute("message", "SignupControllerで例外が発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}
}
