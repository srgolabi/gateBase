package gatebass.register;

import java.util.HashMap;

/**
 *
 * @author reza
 */
public class InitActUser {

    public HashMap<String, ActUser> hashMap = new HashMap<>();

    public static ActUser insert;
    public static ActUser edit;
    public static ActUser view;

    public InitActUser() {

        insert = new ActUser();
        insert.Action = "INSERT";
        insert.id = "1";
        insert.title = "ایجاد شد.";
        hashMap.put("1", insert);

        edit = new ActUser();
        edit.Action = "EDIT";
        edit.id = "2";
        edit.title = "تغییر کرد.";
        hashMap.put("2", edit);

        view = new ActUser();
        view.Action = "VIEW";
        view.id = "3";
        view.title = "مشاهده شد.";
        hashMap.put("3", view);

    }

}
