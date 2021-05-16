package listUtils;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class ListBaseClass<T>
{
    private ObservableList<T> list;
    private Pane container;

    private List<Parent> changeToTargetArray(List<T> list)
    {
        List<Parent> newList = new ArrayList<>();
        for (T temp : list)
        {
            newList.add(createElement(temp).getKey());
        }
        return newList;
    }

    public ListBaseClass(List<T> l, Pane container)
    {
        list = FXCollections.observableArrayList(l);
        this.container=container;

        List<Node> target = container.getChildren();
        List<T> source = list;
        target.clear();
        target.addAll(changeToTargetArray(source));

        ListChangeListener<Parent> listener = change -> {

            while(change.next()) {
                int from = change.getFrom();
                int to = change.getTo();
                if(change.wasPermutated()) {
                    target.subList(from, to).clear();
                    target.addAll(from, changeToTargetArray(source.subList(from, to)));
                } else {
                    target.subList(from, from + change.getRemovedSize()).clear();
                    target.addAll(from, changeToTargetArray(
                            source.subList(from, from + change.getAddedSize()) ));
                }
            }
        };
    }

    protected abstract Pair<Parent, T> createElement(T x);

    public ObservableList<T> getList() {
        return list;
    }


}
