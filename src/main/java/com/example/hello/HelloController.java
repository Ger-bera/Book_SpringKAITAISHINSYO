package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @Controllerアノテーションは、このクラスがSpring MVCのコントローラーであることを示します。
// HTTPリクエストを処理し、適切なビュー（HTML）にマッピングする役割を果たします。
@Controller
public class HelloController {

    // HelloServiceをインジェクトします。これにより、コントローラーはサービス層のメソッドを呼び出すことができます。
    @Autowired
    private HelloService service;

    // GETリクエストで "/hello" にアクセスした際に呼び出されるメソッド
    @GetMapping("/hello")
    public String getHello() {
        // hello.htmlに画面遷移
        return "hello";
    }

    // POSTリクエストで "/hello" にアクセスした際に呼び出されるメソッド
    @PostMapping("/hello")
    public String postRequest(@RequestParam("text1") String str, Model model) {
        // @RequestParamアノテーションで、フォームから送信されたパラメータ "text1" の値を取得
        // 画面から受け取った文字列をModelに登録
        model.addAttribute("sample", str);
        // response.htmlに画面遷移
        return "hello/response";
    }

    // POSTリクエストで "/hello/db" にアクセスした際に呼び出されるメソッド
    @PostMapping("/hello/db")
    public String postDbRequest(@RequestParam("text2") String id, Model model) {

        // サービス層のメソッドを呼び出して、指定されたIDの従業員を1件検索
        Employee employee = service.getEmployee(id);

        // 検索結果をModelに登録
        // これにより、ビュー（HTML）側で ${employee} として参照できるようになります
        model.addAttribute("employee", employee);

        // db.htmlに画面遷移
        return "hello/db";
    }
}
