package figuras;

import com.adaca.memoria.R;

import java.util.ArrayList;
import java.util.List;

public interface Figuras {
    List<Integer> figures = new ArrayList<Integer>() {
        {
            add((R.drawable.circle));
            add((R.drawable.circle));
            add((R.drawable.hexagon));
            add((R.drawable.hexagon));
            add((R.drawable.losango));
            add((R.drawable.losango));
            add((R.drawable.pentagon));
            add((R.drawable.pentagon));
            add((R.drawable.triangulo));
            add((R.drawable.triangulo));
            add((R.drawable.square));
            add((R.drawable.square));
        }
    };
}
