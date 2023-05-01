package es.progcipfpbatoi.modelo.dto;

import es.progcipfpbatoi.exceptions.CategoryErrorException;

public enum Categoria {

    HOGAR, CLASE, JUGAR;

    public static Categoria parse(String categoriaStr) throws CategoryErrorException{

        for (Categoria categoria: Categoria.values()) {
            if (categoria.toString().equals(categoriaStr)) {
                return categoria;
            }
        }

        throw new CategoryErrorException("Categoría desconocida: " + categoriaStr);
    }
}
