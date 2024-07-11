package com.example.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * エラーハンドリングのためのアスペクトクラス
 * データアクセス例外をキャッチし、ログ出力を行う
 */
@Aspect
@Component
@Slf4j
public class ErrorAspect {

	/**
	 * Controller, Service, Repositoryの任意のメソッドで
	 * DataAccessExceptionが発生した場合に実行されるアドバイス
	 *
	 * @param ex 発生した DataAccessException
	 */
	@AfterThrowing(value = "execution(* *..*..*(..))&&"
			+ "(bean(*Controller) || bean(*Service) || bean(*Repository))", throwing = "ex")
	public void throwingNull(DataAccessException ex) {
		// 例外処理の内容（ログ出力）
		log.error("DataAccessExceptionが発生しました");
	}
}
