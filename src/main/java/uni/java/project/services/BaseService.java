package uni.java.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.java.project.repos.BaseRepository;

@Service
public class BaseService<TEntity> {

	private BaseRepository<TEntity> repo;
	
	@Autowired
	public BaseService(BaseRepository<TEntity> repo)
	{
		this.repo = repo;
	}
	
	public TEntity createEntity(TEntity entityToCreate) {
		return repo.saveAndFlush(entityToCreate);
	}
	
	public TEntity updateEntity(TEntity entityToUpdate) {
		return repo.saveAndFlush(entityToUpdate);
	}
	
	public TEntity getById(int id) {
		return repo.findById(id).get();
	}
	
	public List<TEntity> getAll(){
		return repo.findAll();
	}
}
