package ecust.dffuture.dfmapper.qgm;


import java.util.List;

public interface Box {
    void setReference(Quantifier quantifier);

    Quantifier getReference();

    List<Item> getOutput();
}
