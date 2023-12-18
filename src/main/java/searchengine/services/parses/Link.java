package searchengine.services.parses;

import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.CopyOnWriteArraySet;

@Data

public class Link {

    private final String value;
    private Link parent;
    private final Set<Link> children = new CopyOnWriteArraySet<>();
    private int level = 0;
    public static Set<Link> allLinks = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(value, link.value);
    }

    @Override
    public int hashCode() {
        if (value != null)
            return Objects.hash(value.trim());
        else
            return 0;
    }

    public Link(Link parent, @NonNull String value) {
        this.parent = parent;
        if (parent != null)
            parent.addChildren(this);
        this.value = value;
        Link curPar= parent;
        while (curPar != null) {
            level++;
            curPar = curPar.parent;
        }
        allLinks.add(this);
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add(value);
        for (Link child : children) {
            result.add("\t".repeat(level + 1) + child.toString());
        }

        return result.toString();
    }

    public void addChildren(Link children) {
        this.children.add(children);
        children.setParent(this);
    }
}