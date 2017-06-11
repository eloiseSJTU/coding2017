package train.Visitor_Pattern;

/**
 * Created by thomas_young on 1/5/2017.
 */
public class GladiolusVisitor implements Visitor {
    @Override
    public void visit(final GladiolusConcreteElement gladiolus)
    {
        System.out.println(this.getClass().getSimpleName() + " access " + gladiolus.getClass().getSimpleName());
    }

    @Override
    public void visit(final ChrysanthemumConreteElement chrysanthemum)
    {
        System.out.println(this.getClass().getSimpleName() + " access " + chrysanthemum.getClass().getSimpleName());
    }

}
