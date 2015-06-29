package be.persgroep.pe.service.service;

import be.persgroep.pe.service.data.dao.NodeDao;
import be.persgroep.pe.service.domain.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class NodeService {
    @Autowired
    private NodeDao nodeDao;

    @Transactional(readOnly = true)
    public List<Node> searchNodes(final Locale locale, final String query) {
        return this.nodeDao.searchNodes(locale, query);
    }
}
