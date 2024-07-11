package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// @Repositoryアノテーションは、このクラスがデータアクセス層のコンポーネントであることを示します。
// Spring はこのクラスを自動的に検出し、Bean として管理します。
@Repository
public class HelloRepository {

    // @Autowiredアノテーションにより、Spring が自動的にJdbcTemplateのインスタンスを注入します。
    // JdbcTemplateは、JDBCを使用したデータベース操作を簡略化するSpringのユーティリティクラスです。
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 指定されたIDに基づいて従業員データを検索するメソッド
    public Map<String, Object> findByld(String id) {

        // SQL SELECT文
        // 従業員テーブルから指定されたIDに一致するすべての列を取得
        String query = "SELECT *"
                     + " FROM employee"
                     + " WHERE id = ?";

        // JdbcTemplateを使用してクエリを実行し、結果を取得
        // queryForMap メソッドは、1行のデータをMap<String, Object>として返します。第一引数に今回実行するSQLが入っており、第二引数に？に入れる値が入っています
        // キーは列名、値は対応する列の値です
        Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);

        // 検索結果を返す
        return employee;
    }
}
