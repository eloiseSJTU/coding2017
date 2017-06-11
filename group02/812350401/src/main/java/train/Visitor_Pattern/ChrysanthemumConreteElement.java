package train.Visitor_Pattern;

/**
 * Created by thomas_young on 1/5/2017.
 */
public class ChrysanthemumConreteElement implements FlowerElement {
    @Override
    public void accept(final Visitor visitor)
    {
        visitor.visit(this);
    }
}
