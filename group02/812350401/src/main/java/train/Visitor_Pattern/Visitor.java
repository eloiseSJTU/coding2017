package train.Visitor_Pattern;

/**
 * Created by thomas_young on 1/5/2017.
 */
public interface Visitor {
    public void visit(GladiolusConcreteElement gladiolus);

    public void visit(ChrysanthemumConreteElement chrysanthemum);
}
