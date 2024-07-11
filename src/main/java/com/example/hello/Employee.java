package com.example.hello;

import lombok.Data;

// @Dataアノテーションは、Lombokライブラリの機能です。
// これにより、自動的にgetter、setter、toString、equals、hashCodeメソッドが生成されます。
@Data
public class Employee {
    // 従業員IDを表すフィールド
    private String employeeId;

    // 従業員名を表すフィールド
    private String employeeName;

    // 従業員の年齢を表すフィールド
    private int employeeAge;

    // @Dataアノテーションにより、これらのフィールドに対するgetter/setterは自動生成されるため、
    // 明示的に書く必要がありません。
}
