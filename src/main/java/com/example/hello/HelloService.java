package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @Serviceアノテーションは、このクラスがサービス層のコンポーネントであることを示します。
// Spring はこのクラスを自動的に検出し、Bean として管理します。
@Service
public class HelloService {

    // @Autowiredアノテーションにより、Spring が自動的にHelloRepositoryのインスタンスを注入します。
    // これにより、サービス層はリポジトリ層に依存し、データアクセスを行うことができます。
    @Autowired
    private HelloRepository repository;

    /** 従業員を1人取得する */
    public Employee getEmployee(String id) {
        // リポジトリを使用して、指定されたIDの従業員データを検索
        Map<String, Object> map = repository.findByld(id);

        // Mapから各フィールドの値を取得
        // キャストを使用して、適切な型に変換
        String employeeId = (String) map.get("id");
        String name = (String) map.get("name");
        int age = (Integer) map.get("age");

        // 取得した値を使用して、新しいEmployeeオブジェクトを作成
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(name);
        employee.setEmployeeAge(age);

        // 作成したEmployeeオブジェクトを返す
        return employee;
    }
}
