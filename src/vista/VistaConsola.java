package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import pojo.Alumno;
import pojo.Grupo;

public class VistaConsola implements IVista {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        System.out.println("Introduzca Opcion:\n" +
                "1.Insertar Alumno\n" +
                "2.Insertar Grupo\n" +
                "3.Mostrar Alumno\n" +
                "4.Mostrar Grupo\n" +
                "5.Modificar nombre del Alumno por su NIA\n" +
                "6.Eliminar Alumno por su NIA\n" +
                "7.Cambiar alumno de Grupo\n" +
                "8.Guardar datos en Ficheros (JSON, binario txt)\n"+
                "9.Salir");
    }

    @Override
    public Alumno datosAlumno() {
        Alumno a1 = new Alumno();

        System.out.println("Nombre:");
        a1.setNombre(sc.nextLine());

        System.out.println("Apellidos:");
        a1.setApellido(sc.nextLine()); // ✅ CORREGIDO

        System.out.println("Genero (M/F):");
        String genero = sc.nextLine();
        if (genero.length() == 1) {
            a1.setGenero(genero.charAt(0));
        } else {
            System.out.println("Género inválido, usando 'M' por defecto.");
            a1.setGenero('M');
        }

        System.out.println("Fecha de Nacimiento (dd/MM/yyyy):");
        String fecha = sc.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); // ✅ CORREGIDO
        try {
            a1.setFechaNac(format.parse(fecha));
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Usando la fecha actual.");
            a1.setFechaNac(new java.util.Date()); // Usa la fecha actual si el formato es incorrecto
        }

        a1.setGrupo(datosGrupo()); // ✅ CORREGIDO

        return a1;
    }

    @Override
    public Grupo datosGrupo() {
        Grupo g1 = new Grupo();
        System.out.println("Nombre del grupo:");
        g1.setNombre(sc.nextLine()); // ✅ NO PEDIR ID MANUALMENTE
        return g1;
    }

    @Override
    public int pedirInt() {
        System.out.println("Ingrese un número:");
        int opcion=sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    @Override
    public String pedirString() {
        System.out.println("Ingrese una cadena:");
        return sc.nextLine();
    }

    @Override
    public void mostrarAlumnos(ArrayList<Alumno> aLista) {
        for (Alumno a : aLista) {
            System.out.println(a);
        }
    }

    @Override
    public void mostrarGrupos(ArrayList<Grupo> aLista) {
        for (Grupo g : aLista) {
            System.out.println(g);
        }
    }
}
