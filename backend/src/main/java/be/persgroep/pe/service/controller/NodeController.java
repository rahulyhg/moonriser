package be.persgroep.pe.service.controller;

import be.persgroep.pe.service.domain.Node;
import be.persgroep.pe.service.domain.wrapper.SearchNodesResponse;
import be.persgroep.pe.service.service.NodeService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/nodes")
@Api(value = "nodes", description = "Node API")
public class NodeController {
    @Autowired
    private NodeService nodeService;

    @ApiOperation(value = "List Nodes", notes = "Loads a list of nodes")
    @RequestMapping(value = "/{locale}", method = RequestMethod.GET)
    public SearchNodesResponse listNodes(@PathVariable final Locale locale, @RequestParam(required = true) final String query) {
        final List<Node> nodes = this.nodeService.searchNodes(locale, query);

        final SearchNodesResponse searchNodesResponse = new SearchNodesResponse();
        searchNodesResponse.setNodes(nodes);

        return searchNodesResponse;
    }

}
