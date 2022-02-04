package constantes;

import java.util.ArrayList;
import java.util.List;

public interface Constantes {
    int pecas = 12;
    List<Integer> pos = new ArrayList<Integer>() {
        {
            add(0);
            add(0);
            add(1);
            add(1);
            add(2);
            add(2);
            add(3);
            add(3);
            add(4);
            add(4);
            add(5);
            add(5);
        }
    };
}
