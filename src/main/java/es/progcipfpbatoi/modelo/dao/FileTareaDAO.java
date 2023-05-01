package es.progcipfpbatoi.modelo.dao;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.modelo.dto.Categoria;
import es.progcipfpbatoi.modelo.dto.Tarea;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileTareaDAO implements TareaDAO{

    private static final String DATABASE_FILE = "resources/database/tareas.txt";
    private static final int ID = 0;
    private static final int DESCRIPCION = 1;
    private static final int FECHA = 2;
    private static final int FINALIZADO = 3;
    private static final int CATEGORIA = 4;

    private File file;

    public FileTareaDAO() {
        this.file = new File(DATABASE_FILE);
    }

    @Override
    public ArrayList<Tarea> findAll() {
        ArrayList<Tarea> tareas= new ArrayList<>();
        try{
           BufferedReader bufferedReader = new BufferedReader(new FileReader(DATABASE_FILE));
            
            do {
                String line = bufferedReader.readLine();
                if(line == null){
                    return tareas;
                }

                String[] componente = line.split(";");

                int id = Integer.parseInt(componente[ID]);
                String descripcion = componente[DESCRIPCION];
                LocalDateTime fecha = LocalDateTime.parse(componente[FECHA], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                boolean finalizado = Boolean.parseBoolean(componente[FINALIZADO]);
                Categoria categoria = Categoria.valueOf(componente[CATEGORIA]);

                tareas.add(new Tarea(id,descripcion,fecha,finalizado,categoria));

                System.out.println(line);
            }while (true);

        }catch (IOException e){
            System.out.println("Se ha producido durante la lectura del archivo" + e.getMessage());
        }
        return tareas;
    }

    @Override
    public ArrayList<Tarea> findAll(String text) throws DatabaseErrorException {
        ArrayList<Tarea> arrayListTareas= new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(DATABASE_FILE));
            
            do{
                String linea = bufferedReader.readLine();
                if (linea == null) {
                    return arrayListTareas;
                }

                String[] componentes = linea.split(";");
                int id = Integer.parseInt(componentes[ID]);
                String descripcion = componentes[DESCRIPCION];
                LocalDateTime fecha = LocalDateTime.parse(componentes[FECHA], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                boolean finalizado = Boolean.parseBoolean(componentes[FINALIZADO]);
                Categoria categoria = Categoria.valueOf(componentes[CATEGORIA]);

                Tarea tarea = new Tarea(id,descripcion,fecha,finalizado,categoria);
                if (descripcion.startsWith(text)) {
                    arrayListTareas.add(tarea);
                }

            }while (true);
        } catch (IOException e) {
            throw new DatabaseErrorException("No se encuentra el archivo");
        }
    }

    @Override
    public Tarea getById(int id) throws NotFoundException, DatabaseErrorException {
        try (FileReader fileReader = new FileReader(this.file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            do {
                String register = bufferedReader.readLine();
                if (register == null) {
                    throw new NotFoundException("Tarea no encontrada");
                } else if (!register.isBlank()) {
                    String[] fields = register.split(";");
                    int codigo = Integer.parseInt(fields[ID]);
                    String descripcion = fields[DESCRIPCION];
                    LocalDateTime fecha = LocalDateTime.parse(fields[FECHA], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    boolean finalizado = Boolean.parseBoolean(fields[FINALIZADO]);
                    Categoria categoria = Categoria.parse(fields[CATEGORIA]);
                    Tarea tarea = new Tarea(codigo, descripcion, fecha, finalizado, categoria);
                    if (tarea.getId() == id) {
                        return tarea;
                    }
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ocurrió un error en el acceso a la base de datos");
        }
    }

    @Override
    public boolean save(Tarea tarea){
        ArrayList<Tarea> tareas = findAll();
        boolean tareaActualizada = false;
        for (int i = 0; i < tareas.size(); i++) {
            Tarea tareaItem = tareas.get(i);
            if (tareaItem.getDescripcion().equals(tarea.getDescripcion())) {
                tareas.set(i, tarea);
                tareaActualizada = true;
                System.out.println("Tarea actualizada");
            }
        }
        if (!tareaActualizada) {
            tareas.add(tarea);
            System.out.println("Tarea añadida");
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.file))) {
            for (Tarea tareaItem : tareas) {
                String lineaTarea = convertirAString(tareaItem);
                bufferedWriter.write(lineaTarea);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error al guardar la tarea en la base de datos");
        }
        return true;


    }

}
