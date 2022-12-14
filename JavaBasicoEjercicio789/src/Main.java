import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(" ");
        /*
        Dada la función:
        public static String reverse(String texto) { }
        Escribe el código que devuelva una cadena al revés. Por ejemplo, la cadena "hola mundo",
        debe retornar "odnum aloh".
        */
        String texto = reverse("Hola Mundo");
        System.out.println(texto);
        System.out.println(" ");

        /*
        Crea un array unidimensional de Strings y recórrelo, mostrando únicamente sus valores.
        */
        String[] tortugas = new String[]{"Leo","Rafa","Doni","Mike"};
        for (String tortuga : tortugas){
            System.out.println(tortuga);
        }
        System.out.println(" ");

        /*
        Crea un array bidimensional de enteros y recórrelo, mostrando la posición y el valor de cada elemento en ambas dimensiones.
        */
        int[][] bidimensional = new int[2][3];
        bidimensional[0][0] = 1;
        bidimensional[0][1] = 2;
        bidimensional[0][2] = 3;
        bidimensional[1][0] = 11;
        bidimensional[1][1] = 22;
        bidimensional[1][2] = 33;
        for(int i=0; i < bidimensional.length; i++){
            for(int j=0; j < bidimensional[i].length; j++){
                System.out.println("Posicion: " + i + " " + j + " - Valor: " + bidimensional[i][j]);
            }
        }
        System.out.println(" ");

        /*
        Crea un "Vector" del tipo de dato que prefieras, y añádele 5 elementos. Elimina el 2o y 3er elemento
        y muestra el resultado final.
        */
        Vector<Integer> vector = new Vector<>();
        vector.add(5);
        vector.add(6);
        vector.add(7);
        vector.add(8);
        vector.add(9);
        vector.remove(1);
        vector.remove(2);
        System.out.println(vector);
        System.out.println(" ");

        /*
        Indica cuál es el problema de utilizar un Vector con la capacidad por defecto si tuviésemos
        1000 elementos para ser añadidos al mismo.
        */
        //RESPUESTA:
        //El problema es que hace demasiadas copias de sí mismo con cada ampliación, duplicando su capacidad,
        // por lo tanto ocupa demasiado espacio de memoria

        /*
        Crea un ArrayList de tipo String, con 4 elementos. Cópialo en una LinkedList. Recorre ambos mostrando
        únicamente el valor de cada elemento.
        */
        ArrayList<String> familyGuy = new ArrayList<>();
        familyGuy.add("Peter");
        familyGuy.add("Louis");
        familyGuy.add("Chris");
        familyGuy.add("Meg");
        LinkedList<String> linkedFamilyGuy = new LinkedList<>();
        linkedFamilyGuy.add(familyGuy.get(0));
        linkedFamilyGuy.add(familyGuy.get(1));
        linkedFamilyGuy.add(familyGuy.get(2));
        linkedFamilyGuy.add(familyGuy.get(3));
        System.out.println("ArrayList: ");
        for (String personajeFamilyGuy : familyGuy) {
            System.out.println(personajeFamilyGuy);
        }
        System.out.println("LinkedList: ");
        for (String personajeLinkedFamilyGuy : linkedFamilyGuy) {
            System.out.println(personajeLinkedFamilyGuy);
        }
        System.out.println(" ");

        /*
        Crea un ArrayList de tipo int, y, utilizando un bucle rellénalo con elementos 1..10. A continuación,
        con otro bucle, recórrelo y elimina los numeros pares. Por último, vuelve a recorrerlo y muestra el
        ArrayList final. Si te atreves, puedes hacerlo en menos pasos, siempre y cuando cumplas el primer
        "for" de relleno.
        */

        ArrayList<Integer> numeros = new ArrayList<Integer>();
        for (int i=0; i<10; i++){
            numeros.add(i+1);
        }
        for (int j=0; j < numeros.size(); j++){
            if(numeros.get(j)%2==0){
                numeros.remove(j);
            }
        }
        for (int k=0; k < numeros.size(); k++){
            System.out.println(numeros.get(k));
        }
        System.out.println(" ");

        /*
        Crea una función DividePorCero. Esta, debe generar una excepción ("throws") a su llamante del tipo
        ArithmeticException que será capturada por su llamante (desde "main", por ejemplo). Si se dispara la
        excepción, mostraremos el mensaje "Esto no puede hacerse". Finalmente, mostraremos en cualquier caso:
        "Demo de código".
        */

        try {
            DividePorCero(0);
        } catch (ArithmeticException e){
            e.printStackTrace();
        } finally {
            System.out.println("Demo de código");
        }
        System.out.println(" ");

        /*
        Utilizando InputStream y PrintStream, crea una función que reciba dos parámetros: "fileIn" y "fileOut". 
        La tarea de la función será realizar la copia del fichero dado en el parámetro "fileIn" al fichero 
        dado en "fileOut".
        */

        //Lo dejo comentado para que no me esté creando los ficheros todo el rato que hago pruebas.
        //copiarFichero("/fichero", "/ficheroCopiado");


        /*
        Sorpréndenos creando un programa de tu elección que utilice InputStream, PrintStream, excepciones,
        un HashMap y un ArrayList, LinkedList o array.
        */

        //Voy a crear un programa que muestre y actualize listas de películas según su género
        //Hipotéticamente existirían estos archivos

        //hashmap de genero con su ubicación
        HashMap<String, String> ubicaciones = new HashMap<>();
        ubicaciones.put("terror", "/peliculasTerror");
        ubicaciones.put("accion", "/peliculasAccion");
        ubicaciones.put("comedia", "/peliculasComedia");

        //solicito el genero para traer sus peliculas
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el género de la película: ");
        String genero = scan.next();
        System.out.println(" ");
        System.out.println("Lista de películas: ");
        String datos = traerListaPeliculas(ubicaciones.get(genero));
        System.out.println(" ");

        //solicito la nueva película para agregar al archivo
        System.out.println("Ingrese el título de la película: ");
        String titulo = scan.next();
        datos = datos + ". " + titulo;
        actualizarListaPeliculas(datos, genero);
    }

    private static String traerListaPeliculas(String listaDePeliculas) {
        String lista = "";
        try {
            InputStream archivo = new FileInputStream(listaDePeliculas);
            byte[] datos = archivo.readAllBytes(); //Acá hay un array
            for (byte dato: datos) {
                System.out.println((char) dato);
                lista.concat(String.valueOf(dato));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("No se puede leer el archivo: " + e.getMessage());
        }
        return lista;
    }

    private static void actualizarListaPeliculas(String datos, String genero) {
        try{
            OutputStream actualizarArchivo = new PrintStream(genero);
            actualizarArchivo.write(datos.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("No se puede leer el archivo: " + e.getMessage());
        }
    }


    public static String reverse(String texto) {
        String textoAlreves = "";
        int contador = texto.length()-1;
        for (int i = 0; i < texto.length(); i++){
            textoAlreves = textoAlreves + texto.charAt(contador);
            contador--;
        }
        return textoAlreves;
    }

    public static void DividePorCero(int divisor) throws ArithmeticException {
        if(divisor==0){
            throw new ArithmeticException("Esto no puede hacerse");
        }

    }

    private static void copiarFichero(String fileIn, String fileOut) {
        try{
            InputStream fichero = new FileInputStream(fileIn);
            byte[] datos = fichero.readAllBytes();
            PrintStream copiaFichero = new PrintStream(fileOut);
            copiaFichero.write(datos);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}