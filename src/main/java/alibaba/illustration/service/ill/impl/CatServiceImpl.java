package alibaba.illustration.service.ill.impl;

import alibaba.illustration.dao.ill.CategoryDao;
import alibaba.illustration.service.ill.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CatServiceImpl implements CatService {
    @Autowired
    private CategoryDao catDao;

    @Override
    public List<String> queryAllTags() {
        return catDao.queryAllTags();
    }

    @Override
    public List<String> queryAllTypes() {
        return catDao.queryAllTypes();
    }
}
