package train.Visitor_Pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas_young on 1/5/2017.
 */
public class ObjectStructure {
    private final List<FlowerElement> elements = new ArrayList<>();

    public void addElement(final FlowerElement e)
    {
        elements.add(e);
    }

    public void removeElement(final FlowerElement e)
    {
        elements.remove(e);
    }

    public void accept(final Visitor visitor)
    {
        for (final FlowerElement e : elements)
        {
            e.accept(visitor);
        }
    }
}
