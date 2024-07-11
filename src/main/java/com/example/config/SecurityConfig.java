package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Securityの設定クラス
 * WebSecurityConfigurerAdapterを継承し、セキュリティ設定をカスタマイズする
 */
@EnableWebSecurity // Spring Securityの基本的な機能を有効化
@Configuration // このクラスがBean定義のソースであることを示す
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * パスワードエンコーダーのBeanを定義
	 * @return BCryptPasswordEncoderのインスタンス
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * セキュリティの対象外を設定
	 * 静的リソースやH2コンソールへのアクセスをセキュリティチェックから除外
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティを適用しない
		web
				.ignoring()
				.antMatchers("/webjars/**")// WebJars（JavaScript, CSSライブラリ）へのアクセスを許可
				.antMatchers("/css/**")// CSSファイルへのアクセスを許可
				.antMatchers("/js/**")// JavaScriptファイルへのアクセスを許可
				.antMatchers("/h2-console/**");// H2データベースコンソールへのアクセスを許可
	}

	/**
	 * セキュリティの各種設定
	 * HTTPリクエストに対するセキュリティ設定を行う
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// ログイン不要ページの設定
		http
				.authorizeRequests()
				.antMatchers("/login").permitAll() // ログインページは全てのユーザーがアクセス可能
				.antMatchers("/user/signup").permitAll() // ユーザー登録ページは全てのユーザーがアクセス可能
				.antMatchers("/user/signup/rest").permitAll() //直リンクOK
				.antMatchers("/admin").hasAuthority("ROLE_ADMIN") //権限制御
				.anyRequest().authenticated(); // それ以外のページは認証が必要

		// ログイン処理
		http
				.formLogin()
				.loginProcessingUrl("/login")// ログイン処理のパス
				.loginPage("/login")// ログインページの指定
				.failureUrl("/login?error")// ログイン失敗時の遷移先
				.usernameParameter("userId")// ログインページのユーザーID
				.passwordParameter("password")// ログインページのパスワード
				.defaultSuccessUrl("/user/list", true);// 成功後の遷移先

		// ログアウト処理
		http
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout");

		// CSRF対策を無効に設定（一時的）
		// 注意: 本番環境では必ずCSRF対策を有効にすること
		// http.csrf().disable();
	}

	/** 認証の設定 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = passwordEncoder();
		// インメモリ認証
		/*
		auth
				.inMemoryAuthentication()
				.withUser("user")// "user"というユーザーを追加
				.password(encoder.encode("user"))// パスワードをBCryptでハッシュ化
				.roles("GENERAL")// "GENERAL"ロールを付与
				.and()
				.withUser("admin")// "admin"というユーザーを追加
				.password(encoder.encode("admin"))// パスワードをBCryptでハッシュ化
				.roles("ADMIN");// "ADMIN"ロールを付与
		*/

		// ユーザーデータで認証
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(encoder);
	}
}
