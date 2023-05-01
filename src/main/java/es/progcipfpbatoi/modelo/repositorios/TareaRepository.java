package es.progcipfpbatoi.modelo.repositorios;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.modelo.dao.TareaDAO;
import es.progcipfpbatoi.modelo.dto.Tarea;

import java.io.IOException;
import java.util.ArrayList;

public class TareaRepository {

    private TareaDAO tareaDAO;

    public TareaRepository(TareaDAO tareaDAO) {
        this.tareaDAO = tareaDAO;
    }

    public ArrayList<Tarea> findAll() {
        return tareaDAO.findAll();
    }

    public ArrayList<Tarea> findAll(String text) throws DatabaseErrorException {
        return tareaDAO.findAll(text);
    }

    public Tarea getById(int id) throws DatabaseErrorException, NotFoundException {
        return tareaDAO.getById(id);
    }

    public boolean save(Tarea tarea) throws DatabaseErrorException, IOException {
        return tareaDAO.save(tarea);
    }
}
