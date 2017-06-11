package train.Visitor_Pattern;

/**
 * Created by thomas_young on 1/5/2017.
 */
public class ChrysanthemumVisitor implements Visitor {

    @Override
    public void visit(GladiolusConcreteElement gladiolus) {
        System.out.println(this.getClass().getSimpleName() + " access " + gladiolus.getClass().getSimpleName());
    }

    @Override
    public void visit(ChrysanthemumConreteElement chrysanthemum) {
        System.out.println(this.getClass().getSimpleName() + " access " + chrysanthemum.getClass().getSimpleName());
    }
}
