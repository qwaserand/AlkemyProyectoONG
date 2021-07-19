package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.model.Category;
import alkemy.challenge.Challenge.Alkemy.model.News;
import alkemy.challenge.Challenge.Alkemy.repository.CategoryRepository;
import alkemy.challenge.Challenge.Alkemy.repository.NewsRepository;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public Optional<News> findById(long id) {
        return newsRepository.findByIdAndDeletedFalse(id);
    }

    public News create(News news) {
        Optional<Category> category = categoryRepository.findByName("news");
        news.setCategoryId(category.isEmpty() ? categoryRepository.save(new Category("news","news","image")) : category.get());
        return newsRepository.save(news);
    }

    public ResponseEntity<?> update(News news, Long id) {
        Optional<News> newsAux = newsRepository.findById(id);
        if(newsAux.isEmpty()){
            return new ResponseEntity(new Message("no se ha encontrado una news con el id: "+id),
                    HttpStatus.NOT_FOUND);
        }
        newsAux.get().setName(news.getName());
        newsAux.get().setImage(news.getImage());
        newsAux.get().setContent(news.getContent());
        newsAux.get().setCategoryId(news.getCategoryId());
        newsRepository.save(newsAux.get());
        return new ResponseEntity("news actualizado con exito",HttpStatus.ACCEPTED);
    }

    @Transactional
    public void delete(News news) {
        news.setDeleted(true);
        newsRepository.save(news);
    }

    public Page<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public List<News> findAllNews() {
        return newsRepository.findAll();
    }
}
