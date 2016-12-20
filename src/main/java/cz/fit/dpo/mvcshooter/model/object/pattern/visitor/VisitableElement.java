package cz.fit.dpo.mvcshooter.model.object.pattern.visitor;

/**
 * Created by smolijar on 12/19/16.
 */
public interface VisitableElement {
    public void accept(Visitor visitor);
}
