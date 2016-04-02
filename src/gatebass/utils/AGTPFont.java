package gatebass.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author reza
 */
public class AGTPFont {

    private static HashMap<String, String> mChars;

    public static HashMap<String, String> getCharacters() {
        if (mChars == null) {
            HashMap<String, String> aChars = new HashMap<>();
            for (Icons v : Icons.values()) {
                aChars.put(v.name(), v.icon);
            }
            mChars = aChars;
        }
        return mChars;
    }

    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<>();

        for (Icons value : Icons.values()) {
            icons.add(value.name());
        }

        return icons;
    }

    public static enum Icons {

        search("\uE801"),
        plus("\uE810"),
        lock_open_alt("\uE816"),
        eye("\uE818"),
        reply("\uE81B"),
        export("\uE81E"),
        pencil("\uE81F"),
        edit("\uE820"),
        print("\uE821"),
        down_open("\uE82F"),
        left_open("\uE830"),
        right_open("\uE831"),
        up_open("\uE832"),
        credit_card("\uE836"),
        picture("\uE83B"),
        cancel("\uE83D"),
        download("\uE83E"),
        vcard("\uE83F"),
        trash("\uE840"),
        newspaper("\uE841"),
        down_dir("\uE844"),
        left_dir("\uE845"),
        right_dir("\uE846"),
        up_dir("\uE847"),
        level_down("\uE84C"),
        to_end("\uE84D"),
        to_start("\uE84E"),
        doc_new("\uE855"),
        ccw("\uE858");
        /* '' */

        String icon;

        Icons(String character) {
            this.icon = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public String getIcon() {
            return icon;
        }

        public String getName() {
            return name();
        }
    }

}
