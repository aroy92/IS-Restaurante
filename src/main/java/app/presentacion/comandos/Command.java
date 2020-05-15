package app.presentacion.comandos;

import app.presentacion.contexto.Contexto;

public interface Command {
	Contexto ejecutar(Object objeto);
}