package ecust.dffuture.dfmapper.qgm.box;


import ecust.dffuture.dfmapper.qgm.Item;
import ecust.dffuture.dfmapper.qgm.Quantifier;

import java.util.List;

public interface Box {

    void setReference(Quantifier quantifier);

    List<Item> getOutput();
}
