package com.ejemplo.gimnasio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GimnasioController {

    private List<Miembro> miembros = new ArrayList<>();
    private Map<String, String> horarios = new HashMap<>();

    public GimnasioController() {
        miembros.add(new Miembro("Carlos", 30));
        miembros.add(new MiembroPremium("Ana", 25, "Pedro"));
        miembros.add(new MiembroEstudiante("Laura", 20, "SENA"));
        miembros.add(new MiembroPremium("Juan", 35, "Marta"));

        horarios.put("yoga", "Lunes 6:00 PM");
        horarios.put("spinning", "Miércoles 7:00 PM");
        horarios.put("crossfit", "Viernes 5:30 PM");
    }

    @GetMapping("/miembros")
    public List<Miembro> listarMiembros() {
        return miembros;
    }

    @GetMapping("/miembros/buscar/{nombre}")
    public String buscarMiembro(@PathVariable String nombre) {
        for (Miembro m : miembros) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m.descripcion() + " - cuota mensual: $" + m.cuotaMensual() + ".";
            }
        }
        return "No se encontró al miembro " + nombre + ".";
    }

    @GetMapping("/miembros/cuota/{minima}")
    public List<Miembro> miembrosPorCuota(@PathVariable double minima) {
        List<Miembro> resultado = new ArrayList<>();
        for (Miembro m : miembros) {
            if (m.cuotaMensual() >= minima) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    @GetMapping("/clases/{nombre}")
    public String consultarClase(@PathVariable String nombre) {
        for (Map.Entry<String, String> clase : horarios.entrySet()) {
            if (clase.getKey().equalsIgnoreCase(nombre)) {
                return clase.getValue();
            }
        }
        return "Clase no disponible";
    }
}