package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserListForm;

/**
 * ユーザー一覧に関連する処理を担当するコントローラークラス
 */
@Controller
@RequestMapping("/user")
public class UserListController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * ユーザー一覧画面を表示するハンドラーメソッド（GET）
	 *
	 * @param form UserListFormオブジェクト
	 * @param model Springのモデルオブジェクト
	 * @return ユーザー一覧画面のビュー名
	 */
	@GetMapping("/list")
	public String getUserList(@ModelAttribute UserListForm form, Model model) {
		// formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);

		// ユーザー一覧をサービス層から取得
		List<MUser> userList = userService.getUsers(user);

		// 取得したユーザー一覧をModelに追加
		model.addAttribute("userList", userList);

		// ユーザー一覧画面を表示
		return "user/list";
	}

	/**
	 * ユーザー検索処理を行うハンドラーメソッド（POST）
	 *
	 * @param form UserListFormオブジェクト
	 * @param model Springのモデルオブジェクト
	 * @return ユーザー一覧画面のビュー名
	 */
	@PostMapping("/list")
	public String postUserList(@ModelAttribute UserListForm form, Model model) {
		// formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);

		// ユーザー検索
		List<MUser> userList = userService.getUsers(user);

		// Modelに登録
		model.addAttribute("userList", userList);

		// ユーザー一覧画面を表示
		return "user/list";
	}
}
