package train.Visitor_Pattern;

/**
 * Created by thomas_young on 1/5/2017.
 */
public interface FlowerElement {
    public void accept(Visitor visitor);
}
