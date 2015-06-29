package be.persgroep.pe.service.domain.wrapper;

import be.persgroep.pe.service.domain.Node;

import java.util.List;

/**
 * Created by gheylen on 9/01/2015.
 */
public class SearchNodesResponse {
    private List<Node> nodes;

    public List<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(final List<Node> nodes) {
        this.nodes = nodes;
    }
}
