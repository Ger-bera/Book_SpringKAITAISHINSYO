package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;

import lombok.extern.slf4j.Slf4j;

/**
 * ユーザー詳細情報に関する処理を担当するコントローラークラス
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserDetailController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * ユーザー詳細画面を表示するハンドラーメソッド
	 * @param form ユーザー詳細フォーム
	 * @param model ビューに渡すモデル
	 * @param userId パスから取得するユーザーID
	 * @return ビュー名
	 */
	@GetMapping("/detail/{userId:.+}")
	public String getUser(UserDetailForm form, Model model, @PathVariable("userId") String userId) {

		// ユーザーを1件取得
		MUser user = userService.getUserOne(userId);

		// MUserをformに変換
		form = modelMapper.map(user, UserDetailForm.class);

		// 給与情報を明示的に設定
		form.setSalaryList(user.getSalaryList());

		// Modelに登録
		model.addAttribute("userDetailForm", form);

		// ユーザー詳細画面を表示
		return "user/detail";
	}

	/**
	 * ユーザー更新処理
	 * @param form 更新するユーザー情報を含むフォーム
	 * @param model ビューに渡すモデル
	 * @return リダイレクト先のパス
	 */
	@PostMapping(value = "/detail", params = "update")
	public String updateUser(UserDetailForm form, Model model) {
		try {
			// ユーザー更新
			userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
		} catch (Exception e) {
			log.error("ユーザー更新でエラー", e);
		}

		// ユーザー一覧画面にリダイレクト
		return "redirect:/user/list";

	}

	/**
	 * ユーザー削除処理
	 * @param form 削除するユーザーのIDを含むフォーム
	 * @param model ビューに渡すモデル
	 * @return リダイレクト先のパス
	 */
	@PostMapping(value = "/detail", params = "delete")
	public String deleteUser(UserDetailForm form, Model model) {

		// ユーザーを削除
		userService.deleteUserOne(form.getUserId());

		// ユーザー一覧画面にリダイレクト
		return "redirect:/user/list";
	}
}
